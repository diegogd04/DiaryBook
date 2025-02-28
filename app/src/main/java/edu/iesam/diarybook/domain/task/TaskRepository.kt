package edu.iesam.diarybook.domain.task

interface TaskRepository {

    fun getTaskList(): List<Task>
    fun getTaskById(taskId: String): Task
    fun saveTaskList(tasks: List<Task>)
}