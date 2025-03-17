package edu.iesam.diarybook.features.event.domain

import org.koin.core.annotation.Single

@Single
class GetEventListUseCase(private val repository: EventRepository) {

    suspend operator fun invoke(): List<Event> {
        return repository.getEventList()
    }
}