package edu.iesam.diarybook.features.profile.data.remote

import com.google.firebase.auth.FirebaseUser
import edu.iesam.diarybook.features.profile.domain.Profile

fun FirebaseUser.toModel(): Profile {
    return Profile(this.uid, this.displayName, this.email, this.photoUrl)
}