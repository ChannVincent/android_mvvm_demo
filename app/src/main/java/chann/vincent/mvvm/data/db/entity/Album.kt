package chann.vincent.mvvm.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "album")
data class Album(
    @PrimaryKey(autoGenerate = false)
    val id: Int = 0,
    val albumId: Int = 0,
    val thumbnailUrl: String? = null,
    val title: String? = null,
    val url: String? = null
)