package chann.vincent.mvvm.ui.album

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

import chann.vincent.mvvm.R
import chann.vincent.mvvm.ui.base.ScopedFragment
import kotlinx.android.synthetic.main.fragment_album_list.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class AlbumListFragment : ScopedFragment(), KodeinAware {

    override val kodein by closestKodein()
    private val viewModelFactory: AlbumListViewModelFactory by instance()

    companion object {
        fun newInstance() = AlbumListFragment()
    }

    private lateinit var viewModel: AlbumListViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_album_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(AlbumListViewModel::class.java)

        bindUI()
    }

    private fun bindUI() = GlobalScope.launch(Dispatchers.Main) {
        val albumList = viewModel.albumList.await()
        albumList.observe(this@AlbumListFragment, Observer {
            textView.text = it?.getOrNull(0).toString()
        })
    }

}
