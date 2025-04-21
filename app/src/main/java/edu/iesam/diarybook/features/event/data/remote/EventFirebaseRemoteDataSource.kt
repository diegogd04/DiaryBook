package edu.iesam.diarybook.features.event.data.remote

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import edu.iesam.diarybook.features.event.domain.Event
import kotlinx.coroutines.tasks.await
import org.koin.core.annotation.Single

@Single
class EventFirebaseRemoteDataSource(private val firestore: FirebaseFirestore) {

    private val userId = FirebaseAuth.getInstance().currentUser?.uid

    suspend fun getEventList(): List<Event> {
        Log.d("@dev", "currentUser: ${userId}")
        val events = firestore.collection("events")
            .whereEqualTo("userId", userId)
            .get()
            .await()
            .map {
                it.toObject(EventDbModel::class.java).toModel()
            }

        return events
    }
}