package edu.iesam.diarybook.features.task.data.remote

import edu.iesam.diarybook.features.task.domain.Task

fun TaskDbModel.toModel(): Task {
    return Task(this.id, this.title, this.description, this.time, this.userId, this.completed)
}

fun Task.toTaskDbModel(): TaskDbModel {
    return TaskDbModel(
        this.id,
        this.title,
        this.description,
        this.completed,
        this.time,
        this.userId
    )
}