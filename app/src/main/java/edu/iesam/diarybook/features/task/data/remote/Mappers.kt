package edu.iesam.diarybook.features.task.data.remote

import edu.iesam.diarybook.features.task.domain.Task

fun TaskDbModel.toModel(): Task {
    return Task(this.id, this.title, this.description, this.time, this.completed)
}