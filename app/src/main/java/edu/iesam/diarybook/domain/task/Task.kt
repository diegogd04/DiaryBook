package edu.iesam.diarybook.domain.task

data class Task(
    val id: String,
    val title: String,
    val description: String,
    val completed: Boolean,
    val time: Int
)