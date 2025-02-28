package edu.iesam.diarybook.domain.event

class GetEventByIdUseCase(private val repository: EventRepository) {

    operator fun invoke(eventId: String): Event {
        return repository.getEventById(eventId)
    }
}