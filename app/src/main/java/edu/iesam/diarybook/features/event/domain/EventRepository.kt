package edu.iesam.diarybook.features.event.domain

interface EventRepository {

    suspend fun getEventList(): List<Event>
    suspend fun createEvent(event: Event)
    fun getEventById(eventId: String): Event
    fun saveEventList(events: List<Event>)
    fun saveEvent(event: Event)
    fun deleteEventList(events: List<Event>)
    fun deleteEventById(eventId: String)
}