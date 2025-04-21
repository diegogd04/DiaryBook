package edu.iesam.diarybook.features.profile.data.remote

import com.google.firebase.auth.FirebaseAuth
import edu.iesam.diarybook.app.db.AppDataBase
import edu.iesam.diarybook.features.profile.domain.Profile
import org.koin.core.annotation.Single

@Single
class ProfileFirebaseRemoteDataSource(private val db: AppDataBase) {

    private val auth = FirebaseAuth.getInstance()

    init {
        auth.addAuthStateListener { firebaseAuth ->
            val currentUser = firebaseAuth.currentUser
            if (currentUser == null) {
                clearLocalData()
            }
        }
    }

    fun getProfile(): Profile? {
        val user = auth.currentUser?.toModel()

        return user
    }

    fun signOutAccount() {
        auth.signOut()
    }

    fun deleteAccount() {
        auth.currentUser?.delete()
    }

    private fun clearLocalData() {
        db.clearAllTables()
    }
}