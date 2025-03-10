package edu.iesam.diarybook.domain

import org.koin.core.annotation.Single

@Single
class GetActivityListUseCase(private val activityRepository: ActivityRepository) {

    suspend operator fun invoke(): List<Activity> {
        return activityRepository.getActivityList()
    }
}