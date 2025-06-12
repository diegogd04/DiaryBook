package edu.iesam.diarybook.features.event.domain

interface EventRepository {

    suspend fun getEventList(): List<Event>
    suspend fun createEvent(event: Event)
    suspend fun getEventById(eventId: Int): Event
    fun saveEventList(events: List<Event>)
    fun saveEvent(event: Event)
    fun deleteEventList(events: List<Event>)
    fun deleteEventById(eventId: String)
    suspend fun updateEventOld(eventId: Int, old: Boolean)
    suspend fun getCountPendingEventList(events: List<Event>): Int
    suspend fun getCountCompletedEventList(events: List<Event>): Int
}