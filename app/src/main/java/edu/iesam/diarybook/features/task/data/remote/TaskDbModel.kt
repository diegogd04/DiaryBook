package edu.iesam.diarybook.features.task.data.remote

data class TaskDbModel(
    val id: String = "",
    val title: String = "",
    val description: String = "",
    val completed: Boolean = false,
    val time: Long = 0,
    val userId: String = ""
)