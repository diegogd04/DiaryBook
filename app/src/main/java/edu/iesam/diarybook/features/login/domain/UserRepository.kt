package edu.iesam.diarybook.features.login.domain

interface UserRepository {

    fun getAccount(): User?
}