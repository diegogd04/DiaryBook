package edu.iesam.diarybook.app.presentation

import android.net.Uri
import android.widget.ImageView
import coil3.load
import coil3.request.crossfade
import coil3.request.placeholder
import edu.iesam.diarybook.R

fun ImageView.loadUrl(url: Uri?) {
    this.load(url) {
        crossfade(true)
        placeholder(R.drawable.ic_login)
    }
}