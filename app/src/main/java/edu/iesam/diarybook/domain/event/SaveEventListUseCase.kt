package edu.iesam.diarybook.domain.event

class SaveEventListUseCase(private val repository: EventRepository) {

    operator fun invoke(events: List<Event>) {
        repository.saveEventList(events)
    }
}