package edu.iesam.diarybook.features.event.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface EventDao {

    @Query("SELECT * FROM evententity")
    fun getAll(): List<EventEntity>

    @Query("SELECT * FROM evententity WHERE id IN (:eventId)")
    fun getById(eventId: String): EventEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAll(vararg events: EventEntity)

    @Delete
    fun delete(event: EventEntity)
}