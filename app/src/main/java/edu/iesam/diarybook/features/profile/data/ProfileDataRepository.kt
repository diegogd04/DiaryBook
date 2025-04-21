package edu.iesam.diarybook.features.profile.data

import edu.iesam.diarybook.features.profile.data.local.ProfileDbLocalDataSource
import edu.iesam.diarybook.features.profile.data.remote.ProfileFirebaseRemoteDataSource
import edu.iesam.diarybook.features.profile.domain.Profile
import edu.iesam.diarybook.features.profile.domain.ProfileRepository
import org.koin.core.annotation.Single

@Single
class ProfileDataRepository(
    val local: ProfileDbLocalDataSource,
    val remote: ProfileFirebaseRemoteDataSource
) : ProfileRepository {

    override suspend fun getProfile(): Profile? {
        return remote.getProfile()
    }

    override suspend fun signOutAccount() {
        remote.signOutAccount()
        local.clearLocalData()
    }

    override suspend fun deleteAccount() {
        remote.deleteAccount()
        local.clearLocalData()
    }

    override suspend fun clearLocalData() {
        local.clearLocalData()
    }
}