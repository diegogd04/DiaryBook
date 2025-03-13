package edu.iesam.diarybook.data.task.local

import edu.iesam.diarybook.domain.task.Task

fun TaskEntity.toModel(): Task {
    return Task(this.id, this.title, this.description, this.time, this.completed)
}

fun Task.toEntity(): TaskEntity {
    return TaskEntity(this.id, this.title, this.description, this.time, this.completed)
}