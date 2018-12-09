package chann.vincent.mvvm.data.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import chann.vincent.mvvm.data.db.entity.Album
import chann.vincent.mvvm.internal.NoConnectivityException

class AlbumNetworkDataSourceImpl(
    private val leBonCoinApiService: LeBonCoinApiService
) : AlbumNetworkDataSource {

    // only pass liveData to make sure data is only changed in this class
    private val _downloadedAlbumList = MutableLiveData<List<Album>>()
    override val downloadedAlbumList: LiveData<List<Album>>
        get() = _downloadedAlbumList

    override suspend fun fetchAlbumList() {
        try {
            val fetchedAlbumList = leBonCoinApiService
                .getAlbums()
                .await()
            _downloadedAlbumList.postValue(fetchedAlbumList)
        }
        catch (e: NoConnectivityException) {
            Log.e("Connectivity", "No internet connection", e)
        }
    }
}