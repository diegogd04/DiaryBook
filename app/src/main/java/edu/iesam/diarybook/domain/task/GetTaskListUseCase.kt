package edu.iesam.diarybook.domain.task

import org.koin.core.annotation.Single

@Single
class GetTaskListUseCase(private val repository: TaskRepository) {

    suspend operator fun invoke(): List<Task> {
        return repository.getTaskList()
    }
}