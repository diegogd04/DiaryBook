package edu.iesam.diarybook.features.task.data.remote

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import edu.iesam.diarybook.features.task.domain.Task
import kotlinx.coroutines.tasks.await
import org.koin.core.annotation.Single

@Single
class TaskFirebaseRemoteDataSource(private val firestore: FirebaseFirestore) {

    private val userId = FirebaseAuth.getInstance().currentUser?.uid

    suspend fun getTaskList(): List<Task> {
        val tasks = firestore.collection("tasks")
            .whereEqualTo("userId", userId)
            .get()
            .await()
            .map {
                it.toObject(TaskDbModel::class.java).toModel()
            }

        return tasks
    }
}