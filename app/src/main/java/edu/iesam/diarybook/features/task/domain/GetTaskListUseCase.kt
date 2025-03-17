package edu.iesam.diarybook.features.task.domain

import org.koin.core.annotation.Single

@Single
class GetTaskListUseCase(private val repository: TaskRepository) {

    suspend operator fun invoke(): List<Task> {
        return repository.getTaskList()
    }
}