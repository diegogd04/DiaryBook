package edu.iesam.diarybook.domain.event

class DeleteEventByIdUseCase(private val repository: EventRepository) {

    operator fun invoke(eventId: String) {
        repository.deleteEventById(eventId)
    }
}