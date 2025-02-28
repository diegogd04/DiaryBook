package edu.iesam.diarybook.domain.event

class DeleteEventListUseCase(private val repository: EventRepository) {

    operator fun invoke(events: List<Event>) {
        repository.deleteEventList(events)
    }
}