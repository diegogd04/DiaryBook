package edu.iesam.diarybook.domain.task

interface TaskRepository {

    fun getTaskList(): List<Task>
}