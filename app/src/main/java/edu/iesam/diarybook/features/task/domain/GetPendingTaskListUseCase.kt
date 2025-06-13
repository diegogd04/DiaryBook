package edu.iesam.diarybook.features.task.domain

import org.koin.core.annotation.Single

@Single
class GetPendingTaskListUseCase(private val repository: TaskRepository) {

    suspend operator fun invoke(tasks: List<Task>): List<Task> {
        return repository.getPendingTaskList(tasks)
    }
}