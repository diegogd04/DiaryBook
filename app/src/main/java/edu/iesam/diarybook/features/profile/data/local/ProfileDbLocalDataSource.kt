package edu.iesam.diarybook.features.profile.data.local

import edu.iesam.diarybook.app.db.AppDataBase
import org.koin.core.annotation.Single

@Single
class ProfileDbLocalDataSource(private val db: AppDataBase) {

    suspend fun clearLocalData() {
        db.clearAllTables()
    }
}