package edu.iesam.diarybook.features.login.create.domain

import org.koin.core.annotation.Single

@Single
class UpdateAccountUseCase(private val repository: AccountRepository) {

    suspend operator fun invoke(account: Account) {
        repository.updateAccount(account)
    }
}