package edu.iesam.diarybook.features.event.data

import edu.iesam.diarybook.features.event.data.local.EventDbLocalDataSource
import edu.iesam.diarybook.features.event.data.remote.EventFirebaseRemoteDataSource
import edu.iesam.diarybook.features.event.domain.Event
import edu.iesam.diarybook.features.event.domain.EventRepository
import org.koin.core.annotation.Single

@Single
class EventDataRepository(
    private val remote: EventFirebaseRemoteDataSource,
    private val local: EventDbLocalDataSource
) : EventRepository {

    override suspend fun getEventList(): List<Event> {
        val localEvents = local.getEventList()

        return localEvents.ifEmpty {
            val remoteEvents = remote.getEventList()

            local.saveEventList(remoteEvents)
            remoteEvents
        }

    }

    override suspend fun createEvent(event: Event) {
        remote.createEvent(event)
    }

    override suspend fun getEventById(eventId: Int): Event {
        return local.getEventById(eventId)
    }

    override fun saveEventList(events: List<Event>) {
        TODO("Not yet implemented")
    }

    override fun saveEvent(event: Event) {
        TODO("Not yet implemented")
    }

    override fun deleteEventList(events: List<Event>) {
        TODO("Not yet implemented")
    }

    override fun deleteEventById(eventId: String) {
        TODO("Not yet implemented")
    }
}