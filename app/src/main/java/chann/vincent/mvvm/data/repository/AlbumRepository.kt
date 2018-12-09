package chann.vincent.mvvm.data.repository

import androidx.lifecycle.LiveData
import chann.vincent.mvvm.data.db.entity.Album

interface AlbumRepository {

    suspend fun getAlbumList(): LiveData<List<Album>>

}