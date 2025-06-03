package edu.iesam.diarybook.features.event.presentation.eventDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.iesam.diarybook.features.event.domain.Event
import edu.iesam.diarybook.features.event.domain.GetEventByIdUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class EventDetailViewModel(private val getEventByIdUseCase: GetEventByIdUseCase) : ViewModel() {

    private val _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> = _uiState

    fun loadEvent(eventId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val event = getEventByIdUseCase(eventId)
            _uiState.postValue(UiState(event))
        }
    }

    data class UiState(
        val event: Event? = null
    )
}