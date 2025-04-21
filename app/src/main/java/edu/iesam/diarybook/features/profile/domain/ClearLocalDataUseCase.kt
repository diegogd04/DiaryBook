package edu.iesam.diarybook.features.profile.domain

import org.koin.core.annotation.Single

@Single
class ClearLocalDataUseCase(private val repository: ProfileRepository) {

    suspend operator fun invoke() {
        repository.clearLocalData()
    }
}