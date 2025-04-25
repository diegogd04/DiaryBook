package edu.iesam.diarybook.features.profile.data.local

import edu.iesam.diarybook.app.db.AppDataBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.core.annotation.Single

@Single
class ProfileDbLocalDataSource(private val db: AppDataBase) {

    suspend fun clearLocalData() {
        withContext(Dispatchers.IO) {
            db.clearAllTables()
        }
    }
}