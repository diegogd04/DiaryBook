package edu.iesam.diarybook.features.task.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.iesam.diarybook.features.task.domain.GetTaskListUseCase
import edu.iesam.diarybook.features.task.domain.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class TaskListViewModel(private val getTaskListUseCase: GetTaskListUseCase) : ViewModel() {

    private val _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> = _uiState

    fun loadTasks() {
        viewModelScope.launch(Dispatchers.IO) {
            val tasks = getTaskListUseCase()
            _uiState.postValue(UiState(tasks))
        }
    }

    data class UiState(
        val tasks: List<Task> = emptyList()
    )
}