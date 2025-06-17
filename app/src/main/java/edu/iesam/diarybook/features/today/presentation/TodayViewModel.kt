package edu.iesam.diarybook.features.today.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.iesam.diarybook.domain.Activity
import edu.iesam.diarybook.features.task.domain.AddTaskTodayUseCase
import edu.iesam.diarybook.features.today.domain.GetActivitiesTodayUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel
import java.time.LocalDate

@KoinViewModel
class TodayViewModel(
    private val getActivitiesTodayUseCase: GetActivitiesTodayUseCase,
    private val addTaskTodayUseCase: AddTaskTodayUseCase
) : ViewModel() {

    private val _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> = _uiState

    fun loadActivitiesToday(date: LocalDate) {
        viewModelScope.launch(Dispatchers.IO) {
            val activitiesToday = getActivitiesTodayUseCase(date)
            _uiState.postValue(UiState(activitiesToday))
        }
    }

    data class UiState(
        val activitiesToday: List<Activity> = emptyList()
    )
}