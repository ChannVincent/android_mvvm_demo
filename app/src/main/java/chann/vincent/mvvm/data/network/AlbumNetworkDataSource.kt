package chann.vincent.mvvm.data.network

import androidx.lifecycle.LiveData
import chann.vincent.mvvm.data.db.entity.Album

interface AlbumNetworkDataSource {

    val downloadedAlbumList: LiveData<List<Album>>

    suspend fun fetchAlbumList()
}