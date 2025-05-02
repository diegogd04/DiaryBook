package edu.iesam.diarybook.domain

open class Activity(
    val id: String,
    var title: String,
    var description: String,
    var time: Long,
    var userId: String
)