package edu.iesam.diarybook.data.task.remote

import edu.iesam.diarybook.domain.task.Task

fun TaskDbModel.toModel(): Task {
    return Task(this.id, this.title, this.description, this.completed, this.time)
}