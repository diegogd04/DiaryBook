package edu.iesam.diarybook.domain

import java.io.Serializable

open class Activity(
    val id: String,
    var title: String,
    var description: String,
    var time: Long,
    var userId: String
) : Serializable