package com.example.smartnotes.presentation.noteList

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.smartnotes.presentation.viewmodel.NoteViewModel
import androidx.compose.runtime.getValue

@Composable
fun NoteListScreen(navController: NavController, viewModel: NoteViewModel = hiltViewModel()) {
    val notes by viewModel.notes.collectAsState()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate("add_note") }) {
                Text("+")
            }
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).padding(16.dp)) {
            Text("Your Notes", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn {
               items (notes){note->
                   NoteCard(
                       note = note,
                       onClick = { navController.navigate("add_note/${note.id}") },
                       onDelete = { viewModel.deleteNote(note) }
                   )

                   Spacer(modifier = Modifier.height(8.dp))
               }
            }
        }
    }
}
