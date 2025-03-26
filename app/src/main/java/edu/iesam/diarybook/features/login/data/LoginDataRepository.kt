package edu.iesam.diarybook.features.login.data

import edu.iesam.diarybook.features.login.data.remote.LoginFirebaseRemoteDataSource
import edu.iesam.diarybook.features.login.domain.User
import edu.iesam.diarybook.features.login.domain.UserRepository
import org.koin.core.annotation.Single

@Single
class LoginDataRepository(private val remote: LoginFirebaseRemoteDataSource) : UserRepository {

    override fun getAccount(): User? {
        return remote.getAccount()
    }
}