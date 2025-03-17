package edu.iesam.diarybook.features.task.domain

import org.koin.core.annotation.Single

@Single
class DeleteTaskByIdUseCase(private val repository: TaskRepository) {

    operator fun invoke(taskId: String) {
        repository.deleteTaskById(taskId)
    }
}