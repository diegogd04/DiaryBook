package edu.iesam.diarybook.features.task.domain

interface TaskRepository {

    suspend fun getTaskList(): List<Task>
    suspend fun createTask(task: Task)
    fun getTaskById(taskId: Int): Task
    fun saveTaskList(tasks: List<Task>)
    fun saveTask(task: Task)
    fun deleteTaskList(tasks: List<Task>)
    fun deleteTaskById(taskId: String)
    suspend fun updateTaskCompleted(taskId: Int, completed: Boolean)
    suspend fun getPendingTaskList(tasks: List<Task>): List<Task>
    suspend fun getCompletedTaskList(tasks: List<Task>): List<Task>
}