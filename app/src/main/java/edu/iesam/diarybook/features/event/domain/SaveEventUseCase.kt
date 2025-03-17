package edu.iesam.diarybook.features.event.domain

import org.koin.core.annotation.Single

@Single
class SaveEventUseCase(private val repository: EventRepository) {

    operator fun invoke(event: Event) {
        repository.saveEvent(event)
    }
}