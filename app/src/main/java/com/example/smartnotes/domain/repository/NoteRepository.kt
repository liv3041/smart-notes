package com.example.smartnotes.domain.repository

import com.example.smartnotes.data.local.NoteDao
import com.example.smartnotes.domain.model.Note
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NoteRepository @Inject constructor(private val noteDao: NoteDao) {
    val notes: Flow<List<Note>> = noteDao.getAllNotes()
    suspend fun addNote(note: Note) = noteDao.insertNote(note)
    suspend fun deleteNote(note: Note) = noteDao.deleteNote(note)
    fun getNoteById(id: Int): Flow<Note?> {
        return noteDao.getNoteById(id)
    }
    suspend fun updateNote(note: Note) {
        noteDao.updateNote(note)
    }

}