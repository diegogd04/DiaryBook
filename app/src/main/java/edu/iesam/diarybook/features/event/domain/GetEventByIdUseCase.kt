package edu.iesam.diarybook.features.event.domain

import org.koin.core.annotation.Single

@Single
class GetEventByIdUseCase(private val repository: EventRepository) {

    operator fun invoke(eventId: String): Event {
        return repository.getEventById(eventId)
    }
}