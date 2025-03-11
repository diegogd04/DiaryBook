package edu.iesam.diarybook.domain.event

import org.koin.core.annotation.Single

@Single
class DeleteEventListUseCase(private val repository: EventRepository) {

    operator fun invoke(events: List<Event>) {
        repository.deleteEventList(events)
    }
}