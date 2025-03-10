package edu.iesam.diarybook.domain.task

import org.koin.core.annotation.Single

@Single
class SaveTaskUseCase(private val repository: TaskRepository) {

    operator fun invoke(task: Task) {
        repository.saveTask(task)
    }
}