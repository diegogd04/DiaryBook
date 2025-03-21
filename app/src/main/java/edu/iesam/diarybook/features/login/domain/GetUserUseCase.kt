package edu.iesam.diarybook.features.login.domain

import org.koin.core.annotation.Single

@Single
class GetUserUseCase(private val repository: UserRepository) {

    operator fun invoke(): User? {
        return repository.getAccount()
    }
}