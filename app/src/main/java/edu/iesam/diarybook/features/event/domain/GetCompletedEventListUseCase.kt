package edu.iesam.diarybook.features.event.domain

import org.koin.core.annotation.Single

@Single
class GetCompletedEventListUseCase(private val repository: EventRepository) {

    suspend operator fun invoke(events: List<Event>): List<Event> {
        return repository.getCompletedEventList(events)
    }
}