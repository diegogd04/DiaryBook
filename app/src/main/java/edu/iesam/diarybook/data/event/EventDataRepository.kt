package edu.iesam.diarybook.data.event

import edu.iesam.diarybook.data.event.local.EventDbLocalDataSource
import edu.iesam.diarybook.data.event.remote.EventFirebaseRemoteDataSource
import edu.iesam.diarybook.domain.event.Event
import edu.iesam.diarybook.domain.event.EventRepository
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

    override fun getEventById(eventId: String): Event {
        TODO("Not yet implemented")
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