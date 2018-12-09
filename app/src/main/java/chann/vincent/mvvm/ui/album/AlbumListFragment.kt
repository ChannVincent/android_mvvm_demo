package chann.vincent.mvvm.ui.album

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import chann.vincent.mvvm.R
import chann.vincent.mvvm.data.db.entity.Album
import chann.vincent.mvvm.ui.base.ScopedFragment
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.OnItemClickListener
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.fragment_album_list.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance



class AlbumListFragment : ScopedFragment(), KodeinAware, OnItemClickListener {

    override val kodein by closestKodein()
    private val viewModelFactory: AlbumListViewModelFactory by instance()
    private lateinit var viewModel: AlbumListViewModel
    private val groupAdapter = GroupAdapter<ViewHolder>()
    private val maxLoadingItems = 50

    companion object {
        fun newInstance() = AlbumListFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_album_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(AlbumListViewModel::class.java)

        bindUI()
    }

    override fun onItemClick(item: Item<*>, view: View) {
        Toast.makeText(activity, "item ${item.id}", Toast.LENGTH_SHORT).show()
    }

    private fun bindUI() = GlobalScope.launch(Dispatchers.Main) {
        val albumList = viewModel.albumList.await()
        albumList.observe(this@AlbumListFragment, Observer { albums ->
            if (albums == null || albums.isEmpty()) {
                loading_group.visibility = View.VISIBLE
            }
            else {
                loading_group.visibility = View.GONE
                bindRecyclerView(albums)
            }
        })
    }

    private fun bindRecyclerView(albumList: List<Album>) {
        val groupLayoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        groupAdapter.apply {
            setOnItemClickListener(this@AlbumListFragment)
        }

        // populate adapter
        populateAlbumList(albumList)

        recycler_view.apply {
            adapter = groupAdapter
            layoutManager = groupLayoutManager

            // infinite scroll
            addOnScrollListener(object : InfiniteScrollListener(groupLayoutManager) {
                override fun onLoadMore(currentSet: Int) {
                    populateAlbumList(albumList, currentSet)
                }
            })
        }
    }

    private fun populateAlbumList(albumList: List<Album>, currentSet: Int = 0) {
        var count = 0
        for (album in albumList) {
            // take next set of album and add them to groupAdapter
            if (count > maxLoadingItems * currentSet && count < maxLoadingItems * (currentSet + 1)) {
                groupAdapter.add(CardItem(album.id, album.title, album.thumbnailUrl))
            }
            count++
        }
    }
}
