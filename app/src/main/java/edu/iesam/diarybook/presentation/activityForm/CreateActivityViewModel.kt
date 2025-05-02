package edu.iesam.diarybook.presentation.activityForm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.iesam.diarybook.features.event.domain.CreateEventUseCase
import edu.iesam.diarybook.features.event.domain.Event
import edu.iesam.diarybook.features.task.domain.CreateTaskUseCase
import edu.iesam.diarybook.features.task.domain.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class CreateActivityViewModel(
    private val createEventUseCase: CreateEventUseCase,
    private val createTaskUseCase: CreateTaskUseCase
) : ViewModel() {

    private val _uiState = MutableLiveData<UiState>()
    private val uiState: LiveData<UiState> get() = _uiState

    fun createEvent(event: Event) {
        viewModelScope.launch(Dispatchers.IO) {
            createEventUseCase(event)
            _uiState.postValue(UiState(event = event))
        }
    }

    fun createTask(task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            createTaskUseCase(task)
            _uiState.postValue(UiState(task = task))
        }
    }

    data class UiState(
        val event: Event? = null,
        val task: Task? = null
    )
}