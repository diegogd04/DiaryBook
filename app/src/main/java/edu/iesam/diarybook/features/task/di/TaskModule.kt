package edu.iesam.diarybook.features.task.di

import edu.iesam.diarybook.app.db.AppDataBase
import edu.iesam.diarybook.features.task.data.local.TaskDao
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module
@ComponentScan("edu.iesam.diarybook")
class TaskModule {

    @Single
    fun provideTaskDao(db: AppDataBase): TaskDao {
        return db.taskDao()
    }
}