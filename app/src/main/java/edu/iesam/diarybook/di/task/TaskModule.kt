package edu.iesam.diarybook.di.task

import edu.iesam.diarybook.app.db.AppDataBase
import edu.iesam.diarybook.data.task.local.TaskDao
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