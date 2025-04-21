package edu.iesam.diarybook.features.login.create.data.remote

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import edu.iesam.diarybook.features.login.create.domain.Account
import kotlinx.coroutines.tasks.await
import org.koin.core.annotation.Single

@Single
class CreateFirebaseRemoteDataSource {

    private val auth = FirebaseAuth.getInstance()

    suspend fun createAccount(account: Account) {
        auth.createUserWithEmailAndPassword(account.email, account.password).await()
    }

    suspend fun updateAccount(account: Account) {
        val user = auth.currentUser
        if (user != null) {
            val userUpdate = UserProfileChangeRequest.Builder()
                .setDisplayName(account.name)
                .setPhotoUri(account.image)
                .build()

            user.updateProfile(userUpdate).await()
        }
    }
}