package edu.iesam.diarybook.domain.event

import edu.iesam.diarybook.domain.Activity

class Event(
    id: String,
    title: String,
    description: String,
    time: String,
    val date: String,
    val duration: String,
    val old: Boolean,
) : Activity(id, title, description, time)