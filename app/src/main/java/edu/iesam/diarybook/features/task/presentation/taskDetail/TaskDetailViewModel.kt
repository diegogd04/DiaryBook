package edu.iesam.diarybook.features.task.presentation.taskDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.iesam.diarybook.features.task.domain.GetTaskByIdUseCase
import edu.iesam.diarybook.features.task.domain.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class TaskDetailViewModel(private val getTaskByIdUseCase: GetTaskByIdUseCase) : ViewModel() {

    private val _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> = _uiState

    fun loadTask(taskId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val task = getTaskByIdUseCase(taskId)
            _uiState.postValue(UiState(task))
        }
    }

    data class UiState(
        val task: Task? = null
    )
}