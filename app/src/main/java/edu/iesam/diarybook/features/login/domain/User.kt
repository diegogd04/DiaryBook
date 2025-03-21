package edu.iesam.diarybook.features.login.domain

import android.net.Uri

data class User(
    val id: String,
    val name: String?,
    val email: String?,
    val photo: Uri?
)