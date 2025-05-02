package edu.iesam.diarybook.features.task.domain

interface TaskRepository {

    suspend fun getTaskList(): List<Task>
    suspend fun createTask(task: Task)
    fun getTaskById(taskId: String): Task
    fun saveTaskList(tasks: List<Task>)
    fun saveTask(task: Task)
    fun deleteTaskList(tasks: List<Task>)
    fun deleteTaskById(taskId: String)
}