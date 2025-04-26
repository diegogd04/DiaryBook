package edu.iesam.diarybook.features.profile.domain

import org.koin.core.annotation.Single

@Single
class GetProfileUseCase(private val repository: ProfileRepository) {

    suspend operator fun invoke(): Profile? {
        return repository.getProfile()
    }
}