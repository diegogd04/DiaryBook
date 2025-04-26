package edu.iesam.diarybook.features.login.create.domain

import org.koin.core.annotation.Single

@Single
class CreateAccountUseCase(private val repository: AccountRepository) {

    suspend operator fun invoke(account: Account) {
        return repository.createAccount(account)
    }
}