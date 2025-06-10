package edu.iesam.diarybook.features.task.domain

import edu.iesam.diarybook.domain.Activity

class Task(
    id: Int,
    title: String,
    description: String,
    time: Long,
    userId: String,
    var completed: Boolean,
) : Activity(id, title, description, time, userId)