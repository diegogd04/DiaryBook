package edu.iesam.diarybook.domain.event

class GetEventListUseCase(private val repository: EventRepository) {

    operator fun invoke(): List<Event> {
        return repository.getEventList()
    }
}