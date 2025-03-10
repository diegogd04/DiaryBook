package edu.iesam.diarybook.data.task.remote

import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import edu.iesam.diarybook.domain.task.Task
import kotlinx.coroutines.tasks.await
import org.koin.core.annotation.Single

@Single
class TaskFirebaseRemoteDataSource {

    private val db = Firebase.firestore

    suspend fun getTaskList(): List<Task> {
        val tasks = db.collection("tasks")
            .get()
            .await()
            .map {
                it.toObject(TaskDbModel::class.java).toModel()
            }

        return tasks
    }
}