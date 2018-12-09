package chann.vincent.mvvm.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import chann.vincent.mvvm.data.db.entity.Album

@Database(
    entities = [Album::class],
    version = 1
)
abstract class LeBonCoinDatabase: RoomDatabase() {
    abstract fun currentAlbumDao(): AlbumDao

    // singleton constructor
    companion object {
        @Volatile private var instance: LeBonCoinDatabase? = null
        private val LOCK = Any() // avoid 2 threads to instanciate singleton at the same time

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                LeBonCoinDatabase::class.java, "leboncoin.db")
                .build()
    }
}