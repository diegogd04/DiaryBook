package edu.iesam.diarybook.domain.event

interface EventRepository {
    fun getEventList(): List<Event>
}