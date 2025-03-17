package edu.iesam.diarybook.features.task.data.remote

import com.google.firebase.firestore.FirebaseFirestore
import edu.iesam.diarybook.features.task.domain.Task
import kotlinx.coroutines.tasks.await
import org.koin.core.annotation.Single

@Single
class TaskFirebaseRemoteDataSource(private val firestore: FirebaseFirestore) {

    suspend fun getTaskList(): List<Task> {
        val tasks = firestore.collection("tasks")
            .get()
            .await()
            .map {
                it.toObject(TaskDbModel::class.java).toModel()
            }

        return tasks
    }
}