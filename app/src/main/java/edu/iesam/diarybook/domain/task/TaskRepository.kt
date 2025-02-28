package edu.iesam.diarybook.domain.task

interface TaskRepository {

    fun getTaskList(): List<Task>
    fun getTaskById(taskId: String): Task
    fun saveTaskList(tasks: List<Task>)
    fun saveTask(task: Task)
    fun deleteTaskList(tasks: List<Task>)
    fun deleteTaskById(taskId: String)
}