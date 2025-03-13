package edu.iesam.diarybook.di.event

import edu.iesam.diarybook.app.db.AppDataBase
import edu.iesam.diarybook.data.event.local.EventDao
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