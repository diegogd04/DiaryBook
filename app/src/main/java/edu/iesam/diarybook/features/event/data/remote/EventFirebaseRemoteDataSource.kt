package edu.iesam.diarybook.features.event.data.remote

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import edu.iesam.diarybook.features.event.domain.Event
import kotlinx.coroutines.tasks.await
import org.koin.core.annotation.Single

@Single
class EventFirebaseRemoteDataSource(private val firestore: FirebaseFirestore) {

    private val userId get() = FirebaseAuth.getInstance().currentUser?.uid

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
        firestore.collection("events")
            .add(event.toEventDbModel())
            .await()
    }
}