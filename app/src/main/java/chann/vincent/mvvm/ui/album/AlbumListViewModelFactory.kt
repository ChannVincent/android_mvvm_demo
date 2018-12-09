package chann.vincent.mvvm.ui.album

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import chann.vincent.mvvm.data.repository.AlbumRepository

class AlbumListViewModelFactory(
    private val albumRepository: AlbumRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T: ViewModel?> create(modelClass: Class<T>): T {
        return AlbumListViewModel(albumRepository) as T
    }
}