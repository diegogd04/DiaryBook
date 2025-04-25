package edu.iesam.diarybook.features.profile.domain

import org.koin.core.annotation.Single

@Single
class DeleteAccountUseCase(private val repository: ProfileRepository) {

    suspend operator fun invoke(email: String, password: String) {
        repository.deleteAccount(email, password)
    }
}