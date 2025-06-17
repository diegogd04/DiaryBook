package edu.iesam.diarybook.features.today.domain

import edu.iesam.diarybook.domain.Activity
import org.koin.core.annotation.Single
import java.time.LocalDate

@Single
class GetActivitiesTodayUseCase(private val repository: TodayRepository) {

    suspend operator fun invoke(date: LocalDate): List<Activity> {
        return repository.getActivitiesToday(date)
    }
}