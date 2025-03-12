package com.example.hydercontactapp.data.di

import android.app.Application
import androidx.room.Room
import com.example.hydercontactapp.DB_NAME
import com.example.hydercontactapp.data.database.ContactDatabase
import com.example.hydercontactapp.data.repo.ContactRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideContactDatabase(application: Application) : ContactDatabase{
        return  Room.databaseBuilder(
            application,
            ContactDatabase::class.java,
            DB_NAME
        ).fallbackToDestructiveMigrationFrom().build()
    }


}