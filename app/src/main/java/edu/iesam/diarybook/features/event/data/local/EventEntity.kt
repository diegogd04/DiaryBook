package edu.iesam.diarybook.features.event.data.local

import android.net.Uri
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class EventEntity(

    @PrimaryKey @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "time") val time: Long,
    @ColumnInfo(name = "user_id") val userId: String,
    @ColumnInfo(name = "date") val date: String,
    @ColumnInfo(name = "hour") val hour: String,
    @ColumnInfo(name = "duration") val duration: String,
    @ColumnInfo(name = "old") val old: Boolean,
    @ColumnInfo(name = "image") val image: Uri,
    @ColumnInfo(name = "created_at") val createdAt: Long
)