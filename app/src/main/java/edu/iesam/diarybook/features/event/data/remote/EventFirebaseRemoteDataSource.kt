package edu.iesam.diarybook.features.event.data.remote

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
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