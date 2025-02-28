package edu.iesam.diarybook.domain.event

class SaveEventUseCase(private val repository: EventRepository) {

    operator fun invoke(event: Event) {
        repository.saveEvent(event)
    }
}