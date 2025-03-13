package edu.iesam.diarybook.app.db

import androidx.room.Database
import androidx.room.RoomDatabase
import edu.iesam.diarybook.data.event.local.EventDao
import edu.iesam.diarybook.data.event.local.EventEntity

@Database(entities = [EventEntity::class], version = 1)
abstract class AppDataBase : RoomDatabase() {

    abstract fun eventDao(): EventDao
}