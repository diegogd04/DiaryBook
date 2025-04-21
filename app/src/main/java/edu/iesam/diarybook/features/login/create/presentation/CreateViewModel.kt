package edu.iesam.diarybook.features.login.create.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.iesam.diarybook.features.login.create.domain.Account
import edu.iesam.diarybook.features.login.create.domain.CreateAccountUseCase
import edu.iesam.diarybook.features.login.create.domain.UpdateAccountUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class CreateViewModel(
    private val createAccountUseCase: CreateAccountUseCase,
    private val updateAccountUseCase: UpdateAccountUseCase
) : ViewModel() {

    private var _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> = _uiState

    fun createAccount(account: Account) {
        viewModelScope.launch(Dispatchers.IO) {
            createAccountUseCase(account)
            updateAccountUseCase(account)
            _uiState.postValue(UiState(createAccount = true))
        }
    }

    data class UiState(
        val createAccount: Boolean = false
    )
}