package edu.iesam.diarybook.data.task.remote

data class TaskDbModel(
    val id: String = "",
    val title: String = "",
    val description: String = "",
    val completed: Boolean = false,
    val time: String = ""
)