package edu.iesam.diarybook.features.event.data.remote

data class EventDbModel(
    val id: Int = 0,
    val title: String = "",
    val description: String = "",
    val date: String = "",
    val hour: String = "",
    val duration: String = "",
    val old: Boolean = false,
    val time: Long = 0,
    val userId: String = ""
)