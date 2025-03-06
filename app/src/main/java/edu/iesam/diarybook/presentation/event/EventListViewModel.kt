package edu.iesam.diarybook.presentation.event

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.iesam.diarybook.domain.event.Event
import edu.iesam.diarybook.domain.event.GetEventListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class EventListViewModel(private val getEventListUseCase: GetEventListUseCase) : ViewModel() {

    private val _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> = _uiState

    fun loadEvents() {
        viewModelScope.launch(Dispatchers.IO) {
            val events = getEventListUseCase()
            _uiState.postValue(UiState(events))
        }
    }

    data class UiState(
        val events: List<Event> = emptyList()
    )
}