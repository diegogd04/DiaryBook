package edu.iesam.diarybook.features.event.domain

import org.koin.core.annotation.Single

@Single
class SaveEventListUseCase(private val repository: EventRepository) {

    operator fun invoke(events: List<Event>) {
        repository.saveEventList(events)
    }
}