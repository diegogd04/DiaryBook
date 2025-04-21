package edu.iesam.diarybook.features.login.login.domain

import org.koin.core.annotation.Single

@Single
class SignInAccountUseCase(private val repository: AccountRepository) {

    operator fun invoke(account: Account) {
        return repository.signInAccount(account)
    }
}