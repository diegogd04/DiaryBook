package edu.iesam.diarybook.features.login.login.data.remote

import com.google.firebase.auth.FirebaseAuth
import edu.iesam.diarybook.features.login.login.domain.Account
import org.koin.core.annotation.Single

@Single
class LoginFirebaseRemoteDataSource {

    private val auth = FirebaseAuth.getInstance()

    fun signInAccount(account: Account) {
        auth.signInWithEmailAndPassword(account.email, account.password)
    }
}