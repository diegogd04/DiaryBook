package edu.iesam.diarybook.features.profile.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.iesam.diarybook.features.event.domain.GetCountCompletedEventsListUseCase
import edu.iesam.diarybook.features.event.domain.GetCountPendingEventListUseCase
import edu.iesam.diarybook.features.event.domain.GetEventListUseCase
import edu.iesam.diarybook.features.event.presentation.EventListViewModel.UiState
import edu.iesam.diarybook.features.profile.domain.ClearLocalDataUseCase
import edu.iesam.diarybook.features.profile.domain.DeleteAccountUseCase
import edu.iesam.diarybook.features.profile.domain.GetProfileUseCase
import edu.iesam.diarybook.features.profile.domain.Profile
import edu.iesam.diarybook.features.profile.domain.SignOutAccountUseCase
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
    private val getCountPendingEventListUseCase: GetCountPendingEventListUseCase,
    private val getCountCompletedEventsListUseCase: GetCountCompletedEventsListUseCase
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
            val countPendingEvents = getCountPendingEventListUseCase(events)
            val countCompletedEvents = getCountCompletedEventsListUseCase(events)
            _uiState.postValue(
                UiState(
                    countPendingEvents = countPendingEvents,
                    countCompletedEvents = countCompletedEvents
                )
            )
        }
    }

    data class UiState(
        val user: Profile? = null,
        val signOutAccount: Boolean = false,
        val deleteAccount: Boolean = false,
        val countPendingEvents: Int = 0,
        val countCompletedEvents: Int = 0
    )
}