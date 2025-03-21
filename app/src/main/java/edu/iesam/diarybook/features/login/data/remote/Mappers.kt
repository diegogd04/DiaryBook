package edu.iesam.diarybook.features.login.data.remote

import com.google.firebase.auth.FirebaseUser
import edu.iesam.diarybook.features.login.domain.User

fun FirebaseUser.toModel(): User {
    return User(this.uid, this.displayName, this.email, this.photoUrl)
}