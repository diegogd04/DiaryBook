package edu.iesam.diarybook.data.event.local

import edu.iesam.diarybook.domain.event.Event
import org.koin.core.annotation.Single

@Single
class EventDbLocalDataSource(private val eventDao: EventDao) {

    fun getEventList(): List<Event> {
        val events = eventDao.getAll()

        return events.map { event ->
            event.toModel()
        }
    }

    fun saveEventList(events: List<Event>) {
        eventDao.saveAll(*events.map { it.toEntity() }.toTypedArray())
    }
}