package chann.vincent.mvvm.ui.album

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import chann.vincent.mvvm.R

class AlbumListFragment : Fragment() {

    companion object {
        fun newInstance() = AlbumListFragment()
    }

    private lateinit var viewModel: AlbumListViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_album_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(AlbumListViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
