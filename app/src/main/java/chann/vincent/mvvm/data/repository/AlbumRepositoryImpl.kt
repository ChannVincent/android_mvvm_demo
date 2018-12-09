package chann.vincent.mvvm.data.repository

import androidx.lifecycle.LiveData
import chann.vincent.mvvm.data.db.AlbumDao
import chann.vincent.mvvm.data.db.entity.Album
import chann.vincent.mvvm.data.network.AlbumNetworkDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.threeten.bp.ZonedDateTime

class AlbumRepositoryImpl(
    private val currentAlbumDao: AlbumDao,
    private val albumNetworkListDataSource: AlbumNetworkDataSource
) : AlbumRepository {

    var lastFetchTime: ZonedDateTime? = null

    init {
        albumNetworkListDataSource.downloadedAlbumList.observeForever { newAlbumList ->
            persistFetchedAlbumList(newAlbumList)
        }
    }

    override suspend fun getAlbumList(): LiveData<List<Album>> {
        return withContext(Dispatchers.IO) {
            initAlbumListData()
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
        // fetch 1st launch of application and then wait at least 5 minutes before next fetch
        if (lastFetchTime == null || lastFetchTime?.let { isFetchCurrentNeeded(it) } == true) {
            fetchCurrentAlbumList()
            lastFetchTime = ZonedDateTime.now()
        }
    }

    private suspend fun fetchCurrentAlbumList() {
        albumNetworkListDataSource.fetchAlbumList()
    }

    private fun isFetchCurrentNeeded(lastFetchTime: ZonedDateTime): Boolean {
        val fiveMinutesAgo = ZonedDateTime.now().minusMinutes(5)
        return lastFetchTime.isBefore(fiveMinutesAgo)
    }
}