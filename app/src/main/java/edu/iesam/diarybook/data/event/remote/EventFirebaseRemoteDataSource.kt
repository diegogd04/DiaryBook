package edu.iesam.diarybook.data.event.remote

import com.google.firebase.firestore.FirebaseFirestore
import edu.iesam.diarybook.domain.event.Event
import kotlinx.coroutines.tasks.await
import org.koin.core.annotation.Single

@Single
class EventFirebaseRemoteDataSource(private val firestore: FirebaseFirestore) {

    suspend fun getEventList(): List<Event> {
        val events = firestore.collection("events")
            .get()
            .await()
            .map {
                it.toObject(EventDbModel::class.java).toModel()
            }

        return events
    }
}