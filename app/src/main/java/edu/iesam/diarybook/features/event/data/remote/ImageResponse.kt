package edu.iesam.diarybook.features.event.data.remote

data class ImageResponse(
    val created: Long,
    val data: List<ImageData>
)

data class ImageData(
    val url: String
)