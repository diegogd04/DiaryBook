package edu.iesam.diarybook.domain.event

interface EventRepository {

    fun getEventList(): List<Event>
    fun getEventById(eventId: String): Event
    fun saveEventList(events: List<Event>)
    fun saveEvent(event: Event)
    fun deleteEventList(events: List<Event>)
}