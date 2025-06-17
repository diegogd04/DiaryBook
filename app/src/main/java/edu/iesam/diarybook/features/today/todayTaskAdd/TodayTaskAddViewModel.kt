package edu.iesam.diarybook.features.today.todayTaskAdd

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.iesam.diarybook.features.task.domain.AddTaskTodayUseCase
import edu.iesam.diarybook.features.task.domain.Task
import edu.iesam.diarybook.features.today.domain.GetTaskNotCompletedUseCase
import edu.iesam.diarybook.features.today.domain.GetTasksTodayUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class TodayTaskAddViewModel(
    private val getTasksNotCompletedUseCase: GetTaskNotCompletedUseCase,
    private val addTaskTodayUseCase: AddTaskTodayUseCase,
    private val getTasksTodayUseCase: GetTasksTodayUseCase
) : ViewModel() {

    private val _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> = _uiState

    fun loadTasksNotCompleted() {
        viewModelScope.launch(Dispatchers.IO) {
            val tasksNotCompleted = getTasksNotCompletedUseCase()
            _uiState.postValue(UiState(tasksNotCompleted = tasksNotCompleted))
        }
    }

    fun addTaskToday(taskId: Int, today: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            addTaskTodayUseCase(taskId, today)
            getTasksTodayUseCase()
            _uiState.postValue(UiState(addTaskTodaySuccess = true))
        }
    }

    data class UiState(
        val tasksNotCompleted: List<Task> = emptyList(),
        val addTaskTodaySuccess: Boolean = false
    )
}