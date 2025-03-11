package edu.iesam.diarybook.domain.task

import org.koin.core.annotation.Single

@Single
class GetTaskByIdUseCase(private val repository: TaskRepository) {

    operator fun invoke(taskId: String): Task {
        return repository.getTaskById(taskId)
    }
}