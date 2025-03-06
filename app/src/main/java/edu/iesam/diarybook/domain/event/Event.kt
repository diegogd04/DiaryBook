package edu.iesam.diarybook.domain.event

data class Event(
    val id: String,
    val title: String,
    val description: String,
    val date: String,
    val duration: String,
    val old: Boolean,
    val time: String
)