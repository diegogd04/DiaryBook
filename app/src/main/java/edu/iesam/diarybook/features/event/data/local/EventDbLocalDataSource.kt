package edu.iesam.diarybook.features.event.data.local

import edu.iesam.diarybook.app.di.TIME_CACHE
import edu.iesam.diarybook.features.event.domain.Event
import org.koin.core.annotation.Single

@Single
class EventDbLocalDataSource(private val eventDao: EventDao) {

    fun getEventList(): List<Event> {
        val events = eventDao.getAll()

        return if (events.isEmpty()) {
            emptyList()
        } else {
            if (events[0].createdAt.plus(TIME_CACHE) > System.currentTimeMillis()) {
                events.map { event ->
                    event.toModel()
                }
            } else {
                emptyList()
            }
        }
    }

    fun saveEventList(events: List<Event>) {
        val ms = System.currentTimeMillis()

        eventDao.saveAll(*events.map { it.toEntity(ms) }.toTypedArray())
    }
}