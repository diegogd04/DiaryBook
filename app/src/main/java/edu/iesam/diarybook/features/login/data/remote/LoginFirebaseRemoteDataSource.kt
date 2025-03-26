package edu.iesam.diarybook.features.login.data.remote

import com.google.firebase.auth.FirebaseAuth
import edu.iesam.diarybook.features.login.domain.User
import org.koin.core.annotation.Single

@Single
class LoginFirebaseRemoteDataSource(private val auth: FirebaseAuth) {

    fun getAccount(): User? {
        val user = auth.currentUser?.toModel()

        return user
    }
}