package edu.iesam.diarybook.features.event.domain

import org.koin.core.annotation.Single

@Single
class UpdateEventOldUseCase(private val repository: EventRepository) {

    suspend operator fun invoke(eventId: Int, old: Boolean) {
        repository.updateEventOld(eventId, old)
    }
}