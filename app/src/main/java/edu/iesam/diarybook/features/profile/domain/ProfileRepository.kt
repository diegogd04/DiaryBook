package edu.iesam.diarybook.features.profile.domain

interface ProfileRepository {

    suspend fun getProfile(): Profile?
    suspend fun signOutAccount()
    suspend fun deleteAccount()
    suspend fun clearLocalData()
}