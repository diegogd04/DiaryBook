package edu.iesam.diarybook.features.login.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.iesam.diarybook.features.login.domain.GetUserUseCase
import edu.iesam.diarybook.features.login.domain.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class UserViewModel(private val getUserUseCase: GetUserUseCase) : ViewModel() {

    private var _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> = _uiState

    fun loadUser() {
        viewModelScope.launch(Dispatchers.IO) {
            val user = getUserUseCase()
            _uiState.postValue(UiState(user))
        }
    }

    data class UiState(
        val user: User?
    )
}