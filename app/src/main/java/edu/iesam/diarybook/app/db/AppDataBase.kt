package edu.iesam.diarybook.app.db

import androidx.room.Database
import androidx.room.RoomDatabase
import edu.iesam.diarybook.features.event.data.local.EventDao
import edu.iesam.diarybook.features.event.data.local.EventEntity
import edu.iesam.diarybook.features.task.data.local.TaskDao
import edu.iesam.diarybook.features.task.data.local.TaskEntity

@Database(entities = [EventEntity::class, TaskEntity::class], version = 9)
abstract class AppDataBase : RoomDatabase() {

    abstract fun eventDao(): EventDao

    abstract fun taskDao(): TaskDao
}