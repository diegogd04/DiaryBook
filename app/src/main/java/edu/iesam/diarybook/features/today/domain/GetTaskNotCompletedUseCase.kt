package edu.iesam.diarybook.features.today.domain

import edu.iesam.diarybook.features.task.domain.Task
import org.koin.core.annotation.Single

@Single
class GetTaskNotCompletedUseCase(private val repository: TodayRepository) {

    suspend operator fun invoke(): List<Task> {
        return repository.getTaskNotCompleted()
    }
}