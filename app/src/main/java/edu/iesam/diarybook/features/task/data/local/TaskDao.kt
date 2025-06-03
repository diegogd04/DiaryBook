package edu.iesam.diarybook.features.task.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TaskDao {

    @Query("SELECT * FROM taskentity")
    fun getAll(): List<TaskEntity>

    @Query("SELECT * FROM taskentity WHERE id IN (:taskId)")
    fun getById(taskId: String): TaskEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAll(vararg tasks: TaskEntity)

    @Delete
    fun delete(task: TaskEntity)
}