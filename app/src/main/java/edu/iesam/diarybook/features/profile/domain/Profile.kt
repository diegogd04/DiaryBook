package edu.iesam.diarybook.features.profile.domain

import android.net.Uri

data class Profile(
    val id: String,
    val name: String?,
    val email: String?,
    val photo: Uri?
)