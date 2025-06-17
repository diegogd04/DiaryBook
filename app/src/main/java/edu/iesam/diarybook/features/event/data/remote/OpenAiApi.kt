package edu.iesam.diarybook.features.event.data.remote

import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface OpenAiApi {

    @Headers(
        "Content-Type: application/json"
    )
    @POST("v1/images/generations")
    suspend fun generateImage(@Body request: ImageRequest): ImageResponse
}