package edu.iesam.diarybook.features.task.domain

import org.koin.core.annotation.Single

@Single
class AddTaskTodayUseCase(private val repository: TaskRepository) {

    suspend operator fun invoke(taskId: Int, today: Boolean) {
        repository.addTaskToday(taskId, today)
    }
}