package edu.iesam.diarybook.features.login.login.domain

interface AccountRepository {

    fun signInAccount(account: Account)
}