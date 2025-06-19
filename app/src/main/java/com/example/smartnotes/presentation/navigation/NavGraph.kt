package com.example.smartnotes.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.smartnotes.presentation.addNote.AddNoteScreen
import com.example.smartnotes.presentation.noteList.NoteListScreen

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(navController, startDestination = "note_list") {
        composable("note_list") { NoteListScreen(navController) }
        composable("add_note") { AddNoteScreen(navController) }
        composable("add_note/{noteId}") { backStackEntry ->
            val noteId = backStackEntry.arguments?.getString("noteId")?.toIntOrNull()
            AddNoteScreen(navController = navController, noteId = noteId)
        }
    }
}
