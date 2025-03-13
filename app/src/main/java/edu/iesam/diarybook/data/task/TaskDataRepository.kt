package edu.iesam.diarybook.data.task

import edu.iesam.diarybook.data.task.local.TaskDbLocalDataSource
import edu.iesam.diarybook.data.task.remote.TaskFirebaseRemoteDataSource
import edu.iesam.diarybook.domain.task.Task
import edu.iesam.diarybook.domain.task.TaskRepository
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

    override fun getTaskById(taskId: String): Task {
        TODO("Not yet implemented")
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
}