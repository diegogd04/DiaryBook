package edu.iesam.diarybook.features.today.domain

import edu.iesam.diarybook.domain.Activity
import edu.iesam.diarybook.features.event.domain.Event
import edu.iesam.diarybook.features.task.domain.Task
import java.time.LocalDate

interface TodayRepository {

    suspend fun getActivitiesToday(date: LocalDate): List<Activity>
    suspend fun getEventsToday(date: LocalDate): List<Event>
    suspend fun getTaskNotCompleted(): List<Task>
    suspend fun getTasksToday(): List<Task>
}