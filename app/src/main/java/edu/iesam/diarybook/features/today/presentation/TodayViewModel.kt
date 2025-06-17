package edu.iesam.diarybook.features.today.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.iesam.diarybook.features.event.domain.Event
import edu.iesam.diarybook.features.today.domain.GetEventsTodayUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDate

class TodayViewModel(private val getEventsTodayUseCase: GetEventsTodayUseCase) : ViewModel() {

    private val _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> = _uiState

    fun loadEventsToday(date: LocalDate) {
        viewModelScope.launch(Dispatchers.IO) {
            val eventsToday = getEventsTodayUseCase(date)
            _uiState.postValue(UiState(eventsToday))
        }
    }

    data class UiState(
        val eventsToday: List<Event> = emptyList()
    )
}