package edu.iesam.diarybook.features.login.data

import edu.iesam.diarybook.features.login.data.remote.UserFirebaseRemoteDataSource
import edu.iesam.diarybook.features.login.domain.User
import edu.iesam.diarybook.features.login.domain.UserRepository
import org.koin.core.annotation.Single

@Single
class UserDataRepository(private val remote: UserFirebaseRemoteDataSource) : UserRepository {

    override fun getAccount(): User? {
        return remote.getAccount()
    }
}