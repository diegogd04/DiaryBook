package edu.iesam.diarybook.features.today.domain

import edu.iesam.diarybook.features.event.domain.Event
import java.time.LocalDate

interface TodayRepository {

    suspend fun getEventsToday(date: LocalDate): List<Event>
}