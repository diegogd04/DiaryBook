package edu.iesam.diarybook.domain.task

import org.koin.core.annotation.Single

@Single
class SaveTaskListUseCase(private val repository: TaskRepository) {

    operator fun invoke(tasks: List<Task>) {
        repository.saveTaskList(tasks)
    }
}