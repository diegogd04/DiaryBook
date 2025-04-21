package edu.iesam.diarybook.features.login.login.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.iesam.diarybook.features.login.login.domain.Account
import edu.iesam.diarybook.features.login.login.domain.SignInAccountUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class LoginViewModel(private val signInAccountUseCase: SignInAccountUseCase) : ViewModel() {

    private var _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> = _uiState

    fun signInAccount(account: Account) {
        viewModelScope.launch(Dispatchers.IO) {
            signInAccountUseCase(account)
            _uiState.postValue(UiState(signInAccount = true))
        }
    }

    data class UiState(
        val signInAccount: Boolean = false,
    )
}