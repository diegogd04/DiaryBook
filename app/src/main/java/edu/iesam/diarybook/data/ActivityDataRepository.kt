package edu.iesam.diarybook.data

import edu.iesam.diarybook.domain.Activity
import edu.iesam.diarybook.domain.ActivityRepository
import edu.iesam.diarybook.features.event.data.EventDataRepository
import edu.iesam.diarybook.features.task.data.TaskDataRepository
import org.koin.core.annotation.Single

@Single
class ActivityDataRepository(
    private val eventDataRepository: EventDataRepository,
    private val taskDataRepository: TaskDataRepository
) : ActivityRepository {

    override suspend fun getActivityList(): List<Activity> {
        val activities = mutableListOf<Activity>()
        val events = eventDataRepository.getEventList()
        val tasks = taskDataRepository.getTaskList()

        activities.addAll(events)
        activities.addAll(tasks)

        return activities
    }
}