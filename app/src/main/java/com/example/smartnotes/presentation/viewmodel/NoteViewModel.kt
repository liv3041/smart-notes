package com.example.smartnotes.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartnotes.domain.model.Note
import com.example.smartnotes.domain.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(private val repository: NoteRepository) : ViewModel() {
    val notes = repository.notes.stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun addNote(title: String, content: String) {
        viewModelScope.launch {
            repository.addNote(Note(title = title, content = content))
        }
    }
    fun getNoteById(noteId: Int?): Flow<Note?> {
        return if (noteId != null) {
            repository.getNoteById(noteId)
        } else {
            flowOf(null)
        }
    }
    fun updateNote(id: Int, title: String, content: String) {
        viewModelScope.launch {
            val updatedNote = Note(id = id, title = title, content = content)
            repository.updateNote(updatedNote)
        }
    }
    fun deleteNote(note: Note) {
        viewModelScope.launch {
            repository.deleteNote(note)
        }
    }



}