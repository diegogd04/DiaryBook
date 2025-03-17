package edu.iesam.diarybook.features.event.di

import edu.iesam.diarybook.app.db.AppDataBase
import edu.iesam.diarybook.features.event.data.local.EventDao
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module
@ComponentScan("edu.iesam.diarybook")
class EventModule {

    @Single
    fun provideEventDao(db: AppDataBase): EventDao {
        return db.eventDao()
    }
}