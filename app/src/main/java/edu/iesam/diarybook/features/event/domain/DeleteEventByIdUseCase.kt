package edu.iesam.diarybook.features.event.domain

import org.koin.core.annotation.Single

@Single
class DeleteEventByIdUseCase(private val repository: EventRepository) {

    operator fun invoke(eventId: String) {
        repository.deleteEventById(eventId)
    }
}