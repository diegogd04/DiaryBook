package edu.iesam.diarybook.features.today.data

import edu.iesam.diarybook.domain.Activity
import edu.iesam.diarybook.features.event.data.EventDataRepository
import edu.iesam.diarybook.features.event.domain.Event
import edu.iesam.diarybook.features.task.data.TaskDataRepository
import edu.iesam.diarybook.features.task.domain.Task
import edu.iesam.diarybook.features.today.domain.TodayRepository
import org.koin.core.annotation.Single
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

@Single
class TodayDataRepository(
    private val eventDataRepository: EventDataRepository,
    private val taskDataRepository: TaskDataRepository
) : TodayRepository {

    override suspend fun getActivitiesToday(date: LocalDate): List<Activity> {
        val activitiesToday = mutableListOf<Activity>()
        val eventsToday = getEventsToday(date)
        val tasksToday = getTasksToday()

        activitiesToday.addAll(eventsToday)
        activitiesToday.addAll(tasksToday)

        return activitiesToday
    }

    override suspend fun getEventsToday(date: LocalDate): List<Event> {
        val events = eventDataRepository.getEventList()
        val eventsToday = mutableListOf<Event>()
        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.getDefault())

        events.map { event ->
            val eventDate = LocalDate.parse(event.date, formatter)
            if (eventDate == date) {
                eventsToday.add(event)
            }
        }

        return eventsToday
    }

    override suspend fun getTaskNotCompleted(): List<Task> {
        val tasks = taskDataRepository.getTaskList()
        val tasksNotCompleted = mutableListOf<Task>()

        tasks.map { task ->
            if (!task.completed && !task.today) {
                tasksNotCompleted.add(task)
            }
        }

        return tasksNotCompleted
    }

    override suspend fun getTasksToday(): List<Task> {
        val tasks = taskDataRepository.getTaskList()
        val tasksToday = mutableListOf<Task>()

        tasks.map { task ->
            if (task.today) {
                tasksToday.add(task)
            }
        }

        return tasksToday
    }
}