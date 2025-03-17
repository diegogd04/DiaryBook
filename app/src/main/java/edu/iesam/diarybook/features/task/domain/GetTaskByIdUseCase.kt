package edu.iesam.diarybook.features.task.domain

import org.koin.core.annotation.Single

@Single
class GetTaskByIdUseCase(private val repository: TaskRepository) {

    operator fun invoke(taskId: String): Task {
        return repository.getTaskById(taskId)
    }
}