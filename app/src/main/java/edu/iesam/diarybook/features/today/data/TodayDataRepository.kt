package edu.iesam.diarybook.features.today.data

import edu.iesam.diarybook.features.event.data.EventDataRepository
import edu.iesam.diarybook.features.event.domain.Event
import edu.iesam.diarybook.features.today.domain.TodayRepository
import org.koin.core.annotation.Single
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

@Single
class TodayDataRepository(private val eventDataRepository: EventDataRepository) : TodayRepository {

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
}