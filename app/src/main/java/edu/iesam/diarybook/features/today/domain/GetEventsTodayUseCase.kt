package edu.iesam.diarybook.features.today.domain

import edu.iesam.diarybook.features.event.domain.Event
import org.koin.core.annotation.Single
import java.time.LocalDate

@Single
class GetEventsTodayUseCase(private val repository: TodayRepository) {

    suspend operator fun invoke(date: LocalDate): List<Event> {
        return repository.getEventsToday(date)
    }
}