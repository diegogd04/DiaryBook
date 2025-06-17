package edu.iesam.diarybook.features.event.data.remote

data class ImageRequest(
    val prompt: String,
    val n: Int = 1,
    val size: String = "512x512"
)