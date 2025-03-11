package edu.iesam.diarybook.app

import android.app.Application
import com.google.firebase.FirebaseApp
import edu.iesam.diarybook.app.di.AppModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.ksp.generated.module

class DiaryBookApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        FirebaseApp.initializeApp(this)
        startKoin {
            androidContext(this@DiaryBookApplication)
            modules(AppModule().module)
        }
    }
}