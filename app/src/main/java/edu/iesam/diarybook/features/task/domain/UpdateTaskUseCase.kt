package edu.iesam.diarybook.features.task.domain

import org.koin.core.annotation.Single

@Single
class UpdateTaskCompletedUseCase(private val repository: TaskRepository) {

    suspend operator fun invoke(taskId: Int, completed: Boolean) {
        repository.updateTaskCompleted(taskId, completed)
    }
}