package chann.vincent.mvvm.data.repository

import androidx.lifecycle.LiveData
import chann.vincent.mvvm.data.db.AlbumDao
import chann.vincent.mvvm.data.db.entity.Album
import chann.vincent.mvvm.data.network.AlbumNetworkDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AlbumRepositoryImpl(
    private val currentAlbumDao: AlbumDao,
    private val albumNetworkListDataSource: AlbumNetworkDataSource
) : AlbumRepository {

    init {
        albumNetworkListDataSource.downloadedAlbumList.observeForever { newAlbumList ->
            // persist
            persistFetchedAlbumList(newAlbumList)
        }
    }

    override suspend fun getAlbumList(): LiveData<List<Album>> {
        initAlbumListData()
        return withContext(Dispatchers.IO) {
            return@withContext currentAlbumDao.getAlbumList()
        }
    }

    private fun persistFetchedAlbumList(fetchedAlbumList: List<Album>) {
        GlobalScope.launch(Dispatchers.IO) {
            for (album in fetchedAlbumList) {
                currentAlbumDao.insert(album)
            }
        }
    }

    private suspend fun initAlbumListData() {
        fetchCurrentAlbumList()
    }

    private suspend fun fetchCurrentAlbumList() {
        albumNetworkListDataSource.fetchAlbumList()
    }
}