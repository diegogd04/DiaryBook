package edu.iesam.diarybook.features.event.domain

import edu.iesam.diarybook.domain.Activity

class Event(
    id: String,
    title: String,
    description: String,
    time: String,
    userId: String,
    val date: String,
    val hour: String,
    val duration: String,
    val old: Boolean
) : Activity(id, title, description, time, userId)