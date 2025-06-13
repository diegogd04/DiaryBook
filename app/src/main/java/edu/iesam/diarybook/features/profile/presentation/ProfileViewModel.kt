package edu.iesam.diarybook.features.profile.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.iesam.diarybook.features.event.domain.GetCompletedEventListUseCase
import edu.iesam.diarybook.features.event.domain.GetEventListUseCase
import edu.iesam.diarybook.features.event.domain.GetPendingEventListUseCase
import edu.iesam.diarybook.features.profile.domain.ClearLocalDataUseCase
import edu.iesam.diarybook.features.profile.domain.DeleteAccountUseCase
import edu.iesam.diarybook.features.profile.domain.GetProfileUseCase
import edu.iesam.diarybook.features.profile.domain.Profile
import edu.iesam.diarybook.features.profile.domain.SignOutAccountUseCase
import edu.iesam.diarybook.features.task.domain.GetCompletedTaskListUseCase
import edu.iesam.diarybook.features.task.domain.GetPendingTaskListUseCase
import edu.iesam.diarybook.features.task.domain.GetTaskListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class ProfileViewModel(
    private val getUserUseCase: GetProfileUseCase,
    private val signOutAccountUseCase: SignOutAccountUseCase,
    private val deleteAccountUseCase: DeleteAccountUseCase,
    private val clearLocalDataUseCase: ClearLocalDataUseCase,
    private val getEventListUseCase: GetEventListUseCase,
    private val getTaskListUseCase: GetTaskListUseCase,
    private val getPendingEventListUseCase: GetPendingEventListUseCase,
    private val getCompletedEventListUseCase: GetCompletedEventListUseCase,
    private val getPendingTaskListUseCase: GetPendingTaskListUseCase,
    private val getCompletedTaskListUseCase: GetCompletedTaskListUseCase
) : ViewModel() {

    private var _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> = _uiState

    fun loadUser() {
        viewModelScope.launch(Dispatchers.IO) {
            val user = getUserUseCase()
            _uiState.postValue(UiState(user = user))
        }
    }

    fun signOutAccount() {
        viewModelScope.launch(Dispatchers.IO) {
            signOutAccountUseCase()
            clearLocalDataUseCase()
            _uiState.postValue(UiState(signOutAccount = true))
        }
    }

    fun deleteAccount(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteAccountUseCase(email, password)
            _uiState.postValue(UiState(deleteAccount = true))
        }
    }

    fun getCountStatsEventList() {
        viewModelScope.launch(Dispatchers.IO) {
            val events = getEventListUseCase()
            val pendingEvents = getPendingEventListUseCase(events)
            val completedEvents = getCompletedEventListUseCase(events)
            _uiState.postValue(
                UiState(
                    countPendingEvents = pendingEvents.size,
                    countCompletedEvents = completedEvents.size
                )
            )
        }
    }

    fun getCountStatsTaskList() {
        viewModelScope.launch(Dispatchers.IO) {
            val tasks = getTaskListUseCase()
            val pendingTasks = getPendingTaskListUseCase(tasks)
            val completedTasks = getCompletedTaskListUseCase(tasks)
            _uiState.postValue(
                UiState(
                    countPendingTasks = pendingTasks.size,
                    countCompletedTasks = completedTasks.size
                )
            )
        }
    }

    data class UiState(
        val user: Profile? = null,
        val signOutAccount: Boolean = false,
        val deleteAccount: Boolean = false,
        val countPendingEvents: Int = 0,
        val countCompletedEvents: Int = 0,
        val countPendingTasks: Int = 0,
        val countCompletedTasks: Int = 0
    )
}