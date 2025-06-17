package edu.iesam.diarybook.features.event.data.remote

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import edu.iesam.diarybook.features.event.domain.Event
import kotlinx.coroutines.tasks.await
import okhttp3.OkHttpClient
import org.koin.core.annotation.Single
import java.util.concurrent.TimeUnit

@Single
class EventFirebaseRemoteDataSource(
    private val firestore: FirebaseFirestore
) {

    private val userId get() = FirebaseAuth.getInstance().currentUser?.uid
    private val url = "https://api-inference.huggingface.co/models/black-forest-labs/FLUX.1-dev"
    private val client = OkHttpClient.Builder()
        .readTimeout(120, TimeUnit.SECONDS)
        .build()
    //private val token = ""
    //private val apiKey = ""

    suspend fun getEventList(): List<Event> {
        val events = firestore.collection("events")
            .whereEqualTo("userId", userId)
            .get()
            .await()
            .map {
                it.toObject(EventDbModel::class.java).toModel()
            }

        return events
    }

    suspend fun createEvent(event: Event) {
        /*val requestBody = JSONObject()
            .put("inputs", event.title)
            .toString()

        val body = RequestBody.create(
            "application/json".toMediaTypeOrNull(),
            requestBody
        )

        val request = Request.Builder()
            .url(url)
            .addHeader("Authorization", "Bearer $token")
            .post(body)
            .build()

        val response = withContext(Dispatchers.IO) {
            client.newCall(request).execute()
        }
        Log.d("@dev", "Respuesta: ${response.body.toString()}")
        val imageBytes = response.body?.bytes()
        Log.d("@dev", "Respuesta cruda: $imageBytes")

        val imageBase64 =
            android.util.Base64.encodeToString(imageBytes, android.util.Base64.NO_WRAP)
        val requestBodyUpload = FormBody.Builder()
            .add("key", apiKey)
            .add("image", imageBase64)
            .build()
        val requestUpload = Request.Builder()
            .url("https://api.imgbb.com/1/upload")
            .post(requestBodyUpload)
            .build()

        val responseUpload = withContext(Dispatchers.IO) {
            try {
                client.newCall(requestUpload).execute().use { response ->
                    val responseString = response.body?.string()
                    Log.d("@dev", "Respuesta imgbb: $responseString")

                    val json = JSONObject(responseString ?: "")
                    if (json.optBoolean("success")) {
                        json.getJSONObject("data").getString("url")
                    } else {
                        Log.e(
                            "@dev",
                            "Error al subir a imgbb: ${
                                json.optJSONObject("error")?.getString("message")
                            }"
                        )
                    }
                }
            } catch (e: Exception) {
                Log.e("@dev", "Error de red: ${e.message}")
            }
        }

        val json = JSONObject(responseUpload.body?.string() ?: "")
        Log.d("@dev", "Json: $json")
        val imageUrl = json.getJSONObject("data").getString("url")
        event.image = imageUrl*/

        firestore.collection("events")
            .add(event.toEventDbModel())
            .await()
    }

    suspend fun updateEventOld(eventId: Int, old: Boolean) {
        val querySnapshot = getEventDocument(eventId)
        val document = querySnapshot.documents[0].id

        firestore.collection("events")
            .document(document)
            .update("old", old)
            .await()
    }

    private suspend fun getEventDocument(eventId: Int): QuerySnapshot {
        val querySnapshot = firestore.collection("events")
            .whereEqualTo("id", eventId)
            .get()
            .await()

        return querySnapshot
    }
}