package edu.iesam.diarybook.features.login.login.data

import edu.iesam.diarybook.features.login.login.data.remote.LoginFirebaseRemoteDataSource
import edu.iesam.diarybook.features.login.login.domain.Account
import edu.iesam.diarybook.features.login.login.domain.AccountRepository
import org.koin.core.annotation.Single

@Single
class LoginDataRepository(val remote: LoginFirebaseRemoteDataSource) : AccountRepository {

    override fun signInAccount(account: Account) {
        remote.signInAccount(account)
    }
}