package com.example.smartnotes.di

import android.app.Application
import androidx.room.Room
import com.example.smartnotes.data.local.AppDatabase
import com.example.smartnotes.data.local.NoteDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideDatabase(app: Application): AppDatabase =
        Room.databaseBuilder(app, AppDatabase::class.java, "notes_db").build()

    @Provides
    fun provideNoteDao(db: AppDatabase): NoteDao = db.noteDao()
}