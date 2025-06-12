package edu.iesam.diarybook.features.event.domain

import org.koin.core.annotation.Single

@Single
class GetCountPendingEventListUseCase(private val repository: EventRepository) {

    suspend operator fun invoke(events: List<Event>): Int {
        return repository.getCountPendingEventList(events)
    }
}