package edu.iesam.diarybook.features.task.data.local

import edu.iesam.diarybook.features.task.domain.Task

fun TaskEntity.toModel(): Task {
    return Task(this.id, this.title, this.description, this.time, this.completed)
}

fun Task.toEntity(ms: Long): TaskEntity {
    return TaskEntity(this.id, this.title, this.description, this.time, this.completed, ms)
}