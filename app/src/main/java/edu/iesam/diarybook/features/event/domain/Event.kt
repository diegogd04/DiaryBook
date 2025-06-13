package edu.iesam.diarybook.features.event.domain

import android.net.Uri
import edu.iesam.diarybook.domain.Activity

class Event(
    id: Int,
    title: String,
    description: String,
    time: Long,
    userId: String,
    var date: String,
    var hour: String,
    var duration: String,
    var old: Boolean,
    val image: Uri
) : Activity(id, title, description, time, userId)