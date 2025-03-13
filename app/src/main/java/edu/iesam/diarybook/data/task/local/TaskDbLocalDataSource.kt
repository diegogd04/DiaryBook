package edu.iesam.diarybook.data.task.local

import edu.iesam.diarybook.domain.task.Task
import org.koin.core.annotation.Single

@Single
class TaskDbLocalDataSource(private val taskDao: TaskDao) {

    fun getTaskList(): List<Task> {
        val tasks = taskDao.getAll()

        return tasks.map { task ->
            task.toModel()
        }
    }

    fun saveTaskList(tasks: List<Task>) {
        taskDao.saveAll(*tasks.map { it.toEntity() }.toTypedArray())
    }
}