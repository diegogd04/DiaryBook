package edu.iesam.diarybook.features.task.data.local

import edu.iesam.diarybook.app.di.TIME_CACHE
import edu.iesam.diarybook.features.task.domain.Task
import org.koin.core.annotation.Single

@Single
class TaskDbLocalDataSource(private val taskDao: TaskDao) {

    fun getTaskList(): List<Task> {
        val tasks = taskDao.getAll()

        return if (tasks.isEmpty()) {
            emptyList()
        } else {
            if (tasks[0].createdAt.plus(TIME_CACHE) > System.currentTimeMillis()) {
                tasks.map { task ->
                    task.toModel()
                }
            } else {
                emptyList()
            }
        }
    }

    fun saveTaskList(tasks: List<Task>) {
        val ms = System.currentTimeMillis()

        taskDao.saveAll(*tasks.map { it.toEntity(ms) }.toTypedArray())
    }

    fun getTaskById(taskId: Int): Task {
        return taskDao.getById(taskId).toModel()
    }
}