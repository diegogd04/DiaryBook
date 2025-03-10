package edu.iesam.diarybook.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.iesam.diarybook.domain.Activity
import edu.iesam.diarybook.domain.GetActivityListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class ActivityListViewModel(
    private val getActivityListUseCase: GetActivityListUseCase
) : ViewModel() {

    private val _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> = _uiState

    fun loadActivities() {
        viewModelScope.launch(Dispatchers.IO) {
            val activities = getActivityListUseCase()
            _uiState.postValue(UiState(activities))
        }
    }

    data class UiState(
        val activities: List<Activity> = emptyList()
    )
}