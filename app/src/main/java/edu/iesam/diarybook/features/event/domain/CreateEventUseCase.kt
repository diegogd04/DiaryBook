package edu.iesam.diarybook.features.event.domain

import org.koin.core.annotation.Single

@Single
class CreateEventUseCase(private val repository: EventRepository) {

    suspend operator fun invoke(event: Event) {
        repository.createEvent(event)
    }
}