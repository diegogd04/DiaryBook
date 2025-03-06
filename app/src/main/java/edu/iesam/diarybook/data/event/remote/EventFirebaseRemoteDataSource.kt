package edu.iesam.diarybook.data.event.remote

import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import edu.iesam.diarybook.domain.event.Event
import kotlinx.coroutines.tasks.await
import org.koin.core.annotation.Single

@Single
class EventFirebaseRemoteDataSource {

    private val db = Firebase.firestore

    suspend fun getEventList(): List<Event> {
        val events = db.collection("events")
            .get()
            .await()
            .map {
                it.toObject(EventDbModel::class.java).toModel()
            }

        return events
    }
}