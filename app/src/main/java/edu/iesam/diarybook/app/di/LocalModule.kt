package edu.iesam.diarybook.app.di

import android.content.Context
import androidx.room.Room
import edu.iesam.diarybook.app.db.AppDataBase
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

const val TIME_CACHE = 1 * 1000

@Module
@ComponentScan("edu.iesam.diarybook")
class LocalModule {

    @Single
    fun provideDataBase(context: Context): AppDataBase {
        val db = Room.databaseBuilder(
            context,
            AppDataBase::class.java,
            "diary-book-database"
        ).fallbackToDestructiveMigration()

        return db.build()
    }
}