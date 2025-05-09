package edu.iesam.diarybook.app

import android.app.Application
import com.google.firebase.FirebaseApp
import edu.iesam.diarybook.app.di.LocalModule
import edu.iesam.diarybook.app.di.RemoteModule
import edu.iesam.diarybook.features.event.di.EventModule
import edu.iesam.diarybook.features.login.login.di.LoginModule
import edu.iesam.diarybook.features.task.di.TaskModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.ksp.generated.module

class DiaryBookApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        FirebaseApp.initializeApp(this)
        startKoin {
            androidContext(this@DiaryBookApplication)
            modules(
                LocalModule().module,
                RemoteModule().module,
                EventModule().module,
                TaskModule().module,
                LoginModule().module
            )
        }
    }
}