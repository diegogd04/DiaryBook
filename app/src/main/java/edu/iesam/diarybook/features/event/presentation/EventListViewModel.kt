package edu.iesam.diarybook.features.event.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.iesam.diarybook.features.event.domain.Event
import edu.iesam.diarybook.features.event.domain.GetEventListUseCase
import edu.iesam.diarybook.features.event.domain.UpdateEventOldUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class EventListViewModel(
    private val getEventListUseCase: GetEventListUseCase,
    private val updateEventOldUseCase: UpdateEventOldUseCase
) : ViewModel() {

    private val _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> = _uiState

    fun loadEvents() {
        viewModelScope.launch(Dispatchers.IO) {
            val events = getEventListUseCase()
            _uiState.postValue(UiState(events))
        }
    }

    fun setEventOld(event: Event, old: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            updateEventOldUseCase(event.id, old)
            event.apply { this.old = old }
        }
    }

    data class UiState(
        val events: List<Event> = emptyList(),
        val event: Event? = null
    )
}