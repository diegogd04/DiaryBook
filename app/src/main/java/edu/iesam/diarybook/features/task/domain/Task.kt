package edu.iesam.diarybook.features.task.domain

import edu.iesam.diarybook.domain.Activity

class Task(
    id: String,
    title: String,
    description: String,
    time: String,
    val completed: Boolean,
) : Activity(id, title, description, time)