package edu.iesam.diarybook.features.task.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TaskEntity(

    @PrimaryKey @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "time") val time: Long,
    @ColumnInfo(name = "user_id") val userId: String,
    @ColumnInfo(name = "completed") val completed: Boolean,
    @ColumnInfo(name = "created_at") val createdAt: Long
)