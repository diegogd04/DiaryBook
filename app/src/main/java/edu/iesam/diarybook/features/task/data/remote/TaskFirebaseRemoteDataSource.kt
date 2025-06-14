package edu.iesam.diarybook.features.task.data.remote

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import edu.iesam.diarybook.features.task.domain.Task
import kotlinx.coroutines.tasks.await
import org.koin.core.annotation.Single

@Single
class TaskFirebaseRemoteDataSource(private val firestore: FirebaseFirestore) {

    private val userId get() = FirebaseAuth.getInstance().currentUser?.uid

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

    suspend fun createTask(task: Task) {
        firestore.collection("tasks")
            .add(task.toTaskDbModel())
            .await()
    }

    suspend fun updateTaskCompleted(taskId: Int, completed: Boolean) {
        val querySnapshot = getTaskDocument(taskId)
        val document = querySnapshot.documents[0].id

        firestore.collection("tasks")
            .document(document)
            .update("completed", completed)
            .await()
    }

    private suspend fun getTaskDocument(taskId: Int): QuerySnapshot {
        val querySnapshot = firestore.collection("tasks")
            .whereEqualTo("id", taskId)
            .get()
            .await()

        return querySnapshot
    }
}