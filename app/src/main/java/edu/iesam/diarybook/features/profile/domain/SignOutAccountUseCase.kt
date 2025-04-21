package edu.iesam.diarybook.features.profile.domain

import org.koin.core.annotation.Single

@Single
class SignOutAccountUseCase(private val repository: ProfileRepository) {

    suspend operator fun invoke() {
        repository.signOutAccount()
    }
}