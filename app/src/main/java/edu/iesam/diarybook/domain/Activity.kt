package edu.iesam.diarybook.domain

import java.io.Serializable

open class Activity(
    var id: Int = 0,
    var title: String,
    var description: String,
    var time: Long,
    var userId: String
) : Serializable