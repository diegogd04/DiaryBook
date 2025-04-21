package edu.iesam.diarybook.features.login.create.domain

import android.net.Uri

data class Account(
    var name: String,
    var email: String,
    var password: String,
    var image: Uri?
)