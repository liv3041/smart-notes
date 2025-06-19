package com.example.smartnotes.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.smartnotes.domain.model.Note

@Database(entities = [Note::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}