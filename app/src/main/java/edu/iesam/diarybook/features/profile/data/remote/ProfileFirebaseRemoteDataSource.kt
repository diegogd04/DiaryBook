package edu.iesam.diarybook.features.profile.data.remote

import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthRecentLoginRequiredException
import edu.iesam.diarybook.features.profile.domain.Profile
import kotlinx.coroutines.tasks.await
import org.koin.core.annotation.Single

@Single
class ProfileFirebaseRemoteDataSource {

    private val auth get() = FirebaseAuth.getInstance()

    fun getProfile(): Profile? {
        val user = auth.currentUser?.toModel()

        return user
    }

    fun signOutAccount() {
        auth.signOut()
    }

    suspend fun deleteAccount(email: String, password: String) {
        val user = FirebaseAuth.getInstance().currentUser

        try {
            user?.delete()?.await()
        } catch (e: FirebaseAuthRecentLoginRequiredException) {
            val credential = EmailAuthProvider.getCredential(email, password)
            user?.reauthenticate(credential)?.await()
            user?.delete()?.await()
        }
    }
}