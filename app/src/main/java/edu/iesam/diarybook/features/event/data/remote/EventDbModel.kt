package edu.iesam.diarybook.features.event.data.remote

data class EventDbModel(
    val id: String = "",
    val title: String = "",
    val description: String = "",
    val date: String = "",
    val duration: String = "",
    val old: Boolean = false,
    val time: String = ""
)