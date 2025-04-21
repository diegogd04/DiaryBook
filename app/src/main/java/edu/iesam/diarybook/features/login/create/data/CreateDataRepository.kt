package edu.iesam.diarybook.features.login.create.data

import edu.iesam.diarybook.features.login.create.data.remote.CreateFirebaseRemoteDataSource
import edu.iesam.diarybook.features.login.create.domain.Account
import edu.iesam.diarybook.features.login.create.domain.AccountRepository
import org.koin.core.annotation.Single

@Single
class CreateDataRepository(val remote: CreateFirebaseRemoteDataSource) : AccountRepository {

    override suspend fun createAccount(account: Account) {
        remote.createAccount(account)
    }

    override suspend fun updateAccount(account: Account) {
        remote.updateAccount(account)
    }
}