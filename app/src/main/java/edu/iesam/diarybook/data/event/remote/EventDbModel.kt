package edu.iesam.diarybook.data.event.remote

data class EventDbModel(
    val id: String = "",
    val title: String = "",
    val description: String = "",
    val date: String = "",
    val duration: String = "",
    val old: Boolean = false,
    val time: String = ""
)