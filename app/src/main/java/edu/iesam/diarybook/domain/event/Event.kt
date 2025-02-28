package edu.iesam.diarybook.domain.event

data class Event(
    val id: String,
    val title: String,
    val description: String,
    val date: Int,
    val duration: Int,
    val old: Boolean,
    val time: Int
)