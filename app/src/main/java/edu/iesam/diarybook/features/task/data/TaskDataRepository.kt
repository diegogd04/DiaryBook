package edu.iesam.diarybook.features.task.data

import edu.iesam.diarybook.features.task.data.local.TaskDbLocalDataSource
import edu.iesam.diarybook.features.task.data.remote.TaskFirebaseRemoteDataSource
import edu.iesam.diarybook.features.task.domain.Task
import edu.iesam.diarybook.features.task.domain.TaskRepository
import org.koin.core.annotation.Single

@Single
class TaskDataRepository(
    private val remote: TaskFirebaseRemoteDataSource,
    private val local: TaskDbLocalDataSource
) : TaskRepository {

    override suspend fun getTaskList(): List<Task> {
        val localTasks = local.getTaskList()

        return localTasks.ifEmpty {
            val remoteTasks = remote.getTaskList()

            local.saveTaskList(remoteTasks)
            remoteTasks
        }
    }

    override suspend fun createTask(task: Task) {
        remote.createTask(task)
    }

    override fun getTaskById(taskId: Int): Task {
        return local.getTaskById(taskId)
    }

    override fun saveTaskList(tasks: List<Task>) {
        TODO("Not yet implemented")
    }

    override fun saveTask(task: Task) {
        TODO("Not yet implemented")
    }

    override fun deleteTaskList(tasks: List<Task>) {
        TODO("Not yet implemented")
    }

    override fun deleteTaskById(taskId: String) {
        TODO("Not yet implemented")
    }

    override suspend fun updateTaskCompleted(taskId: Int, completed: Boolean) {
        remote.updateTaskCompleted(taskId, completed)
    }

    override suspend fun getPendingTaskList(tasks: List<Task>): List<Task> {
        return local.getPendingTaskList(tasks)
    }

    override suspend fun getCompletedTaskList(tasks: List<Task>): List<Task> {
        return local.getCompletedTaskList(tasks)
    }
}