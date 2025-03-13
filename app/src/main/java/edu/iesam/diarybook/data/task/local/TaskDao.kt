package edu.iesam.diarybook.data.task.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TaskDao {

    @Query("SELECT * FROM taskentity")
    fun getAll(): List<TaskEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAll(vararg tasks: TaskEntity)

    @Delete
    fun delete(task: TaskEntity)
}