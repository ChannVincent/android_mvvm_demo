package chann.vincent.mvvm.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import chann.vincent.mvvm.data.db.entity.Album

@Dao
interface AlbumDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(album: Album)

    @Query("select * from album")
    fun getAlbumList(): LiveData<List<Album>>
}