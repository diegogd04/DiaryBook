package edu.iesam.diarybook.features.login.create.domain

interface AccountRepository {

    suspend fun createAccount(account: Account)
    suspend fun updateAccount(account: Account)
}