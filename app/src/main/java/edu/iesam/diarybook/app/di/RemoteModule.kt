package edu.iesam.diarybook.app.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module
@ComponentScan("edu.iesam.diarybook")
class RemoteModule {

    @Single
    fun provideFirebase(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    @Single
    fun provideAuthentication(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }
}