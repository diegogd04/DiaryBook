package edu.iesam.diarybook.features.task.domain

import org.koin.core.annotation.Single

@Single
class CreateTaskUseCase(private val repository: TaskRepository) {

    suspend operator fun invoke(task: Task) {
        repository.createTask(task)
    }
}