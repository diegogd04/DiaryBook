package edu.iesam.diarybook.features.task.domain

import org.koin.core.annotation.Single

@Single
class SaveTaskListUseCase(private val repository: TaskRepository) {

    operator fun invoke(tasks: List<Task>) {
        repository.saveTaskList(tasks)
    }
}