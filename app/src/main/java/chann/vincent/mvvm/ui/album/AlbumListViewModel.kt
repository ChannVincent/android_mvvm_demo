package chann.vincent.mvvm.ui.album

import androidx.lifecycle.ViewModel;
import chann.vincent.mvvm.data.repository.AlbumRepository
import chann.vincent.mvvm.internal.lazyDeferred

class AlbumListViewModel(
    albumRepository: AlbumRepository
) : ViewModel() {

    val albumList by lazyDeferred {
        albumRepository.getAlbumList()
    }
}
