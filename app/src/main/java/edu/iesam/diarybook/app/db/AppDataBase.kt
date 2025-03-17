package edu.iesam.diarybook.app.db

import androidx.room.Database
import androidx.room.RoomDatabase
import edu.iesam.diarybook.data.event.local.EventDao
import edu.iesam.diarybook.data.event.local.EventEntity
import edu.iesam.diarybook.data.task.local.TaskDao
import edu.iesam.diarybook.data.task.local.TaskEntity

@Database(entities = [EventEntity::class, TaskEntity::class], version = 3)
abstract class AppDataBase : RoomDatabase() {

    abstract fun eventDao(): EventDao

    abstract fun taskDao(): TaskDao
}