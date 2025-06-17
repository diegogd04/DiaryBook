package edu.iesam.diarybook.features.today.data

import edu.iesam.diarybook.features.event.data.EventDataRepository
import edu.iesam.diarybook.features.event.domain.Event
import edu.iesam.diarybook.features.today.domain.TodayRepository
import java.time.LocalDate

class TodayDataRepository(private val eventDataRepository: EventDataRepository) : TodayRepository {

    override suspend fun getEventsToday(date: LocalDate): List<Event> {
        val events = eventDataRepository.getEventList()
        val eventsToday = mutableListOf<Event>()

        events.map { event ->
            val eventDate = LocalDate.parse(event.date)
            if (eventDate == date) {
                eventsToday.add(event)
            }
        }

        return eventsToday
    }
}