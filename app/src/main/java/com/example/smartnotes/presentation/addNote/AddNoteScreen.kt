////
////package com.example.smartnotes.presentation.addNote
////
////import android.net.Uri
////import androidx.activity.compose.rememberLauncherForActivityResult
////import androidx.activity.result.contract.ActivityResultContracts
////import androidx.compose.foundation.Image
////import androidx.compose.foundation.gestures.detectTapGestures
////import androidx.compose.foundation.layout.*
////import androidx.compose.foundation.rememberScrollState
////import androidx.compose.foundation.verticalScroll
////import androidx.compose.material3.*
////import androidx.compose.runtime.*
////import androidx.compose.ui.Modifier
////import androidx.compose.ui.input.pointer.pointerInput
////import androidx.compose.ui.platform.LocalContext
////import androidx.compose.ui.unit.dp
////import androidx.hilt.navigation.compose.hiltViewModel
////import androidx.navigation.NavController
////import coil.compose.rememberAsyncImagePainter
////import com.example.smartnotes.presentation.viewmodel.NoteViewModel
////import dev.jeziellago.compose.markdowntext.MarkdownText
////
////@OptIn(ExperimentalMaterial3Api::class)
////@Composable
////fun AddNoteScreen(
////    navController: NavController,
////    noteId: Int? = null,
////    viewModel: NoteViewModel = hiltViewModel()
////) {
////    val context = LocalContext.current
////    val noteToEdit by viewModel.getNoteById(noteId).collectAsState(initial = null)
////
////    var title by remember { mutableStateOf("") }
////    var content by remember { mutableStateOf("") }
////    var isPreviewMode by remember { mutableStateOf(false) }
////    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
////
////    val imagePickerLauncher = rememberLauncherForActivityResult(
////        contract = ActivityResultContracts.GetContent()
////    ) { uri: Uri? ->
////        selectedImageUri = uri
////    }
////
////    LaunchedEffect(noteToEdit) {
////        noteToEdit?.let {
////            title = it.title
////            content = it.content
////            // You can load existing image URI if stored previously
////        }
////    }
////
////    Scaffold(
////        topBar = {
////            TopAppBar(
////                title = { Text("Add Note") },
////                actions = {
////                    Text(
////                        text = if (isPreviewMode) "Edit" else "Preview",
////                        modifier = Modifier
////                            .padding(horizontal = 16.dp)
////                            .pointerInput(Unit) {
////                                detectTapGestures {
////                                    isPreviewMode = !isPreviewMode
////                                }
////                            }
////                    )
////                }
////            )
////        },
////        floatingActionButton = {
////            FloatingActionButton(
////                onClick = {
////                    if (title.isNotBlank() && content.isNotBlank()) {
////                        if (noteId != null) {
////                            viewModel.updateNote(noteId, title, content)
////                        } else {
////                            viewModel.addNote(title, content)
////                        }
////                        navController.popBackStack()
////                    }
////                }
////            ) {
////                Text("✓")
////            }
////        }
////    ) { padding ->
////        Column(
////            modifier = Modifier
////                .padding(padding)
////                .padding(16.dp)
////                .verticalScroll(rememberScrollState())
////        ) {
////            if (isPreviewMode) {
////                Text(
////                    text = title,
////                    style = MaterialTheme.typography.titleLarge,
////                    modifier = Modifier.padding(bottom = 8.dp)
////                )
////                MarkdownText(
////                    markdown = content,
////                    modifier = Modifier.fillMaxWidth()
////                )
////                selectedImageUri?.let {
////                    Spacer(modifier = Modifier.height(16.dp))
////                    Text("Attached Image:", style = MaterialTheme.typography.bodyMedium)
////                    Image(
////                        painter = rememberAsyncImagePainter(it),
////                        contentDescription = "Attached Image",
////                        modifier = Modifier
////                            .fillMaxWidth()
////                            .heightIn(min = 180.dp)
////                            .padding(top = 8.dp)
////                    )
////                }
////            } else {
////                OutlinedTextField(
////                    value = title,
////                    onValueChange = { title = it },
////                    placeholder = { Text("Title") },
////                    modifier = Modifier
////                        .fillMaxWidth()
////                        .padding(bottom = 16.dp),
////                    singleLine = true
////                )
////                OutlinedTextField(
////                    value = content,
////                    onValueChange = { content = it },
////                    placeholder = { Text("Start writing your note...") },
////                    modifier = Modifier
////                        .fillMaxWidth()
////                        .heightIn(min = 400.dp),
////                    maxLines = Int.MAX_VALUE
////                )
////                Spacer(modifier = Modifier.height(16.dp))
////
////                Button(
////                    onClick = { imagePickerLauncher.launch("image/*") },
////                    modifier = Modifier.fillMaxWidth()
////                ) {
////                    Text("Attach Image")
////                }
////
////                selectedImageUri?.let {
////                    Spacer(modifier = Modifier.height(16.dp))
////                    Text("Image Preview:", style = MaterialTheme.typography.bodyMedium)
////                    Image(
////                        painter = rememberAsyncImagePainter(it),
////                        contentDescription = "Selected Image",
////                        modifier = Modifier
////                            .fillMaxWidth()
////                            .heightIn(min = 180.dp)
////                            .padding(top = 8.dp)
////                    )
////                }
////            }
////        }
////    }
////}
//package com.example.smartnotes.presentation.addNote
//
//import android.net.Uri
//import androidx.activity.compose.rememberLauncherForActivityResult
//import androidx.activity.result.contract.ActivityResultContracts
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.gestures.detectTapGestures
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.rememberScrollState
//import androidx.compose.foundation.text.BasicTextField
//import androidx.compose.foundation.verticalScroll
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.input.pointer.pointerInput
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.text.TextRange
//import androidx.compose.ui.text.TextStyle
//import androidx.compose.ui.text.font.FontFamily
//import androidx.compose.ui.text.font.SystemFontFamily
//import androidx.compose.ui.text.input.TextFieldValue
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.hilt.navigation.compose.hiltViewModel
//import androidx.navigation.NavController
//import coil.compose.rememberAsyncImagePainter
//import com.example.smartnotes.presentation.viewmodel.NoteViewModel
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun AddNoteScreen(
//    navController: NavController,
//    noteId: Int? = null,
//    viewModel: NoteViewModel = hiltViewModel()
//) {
//    val noteToEdit by viewModel.getNoteById(noteId).collectAsState(initial = null)
//
//    var textFieldValue by remember { mutableStateOf(TextFieldValue("")) }
//    var isPreviewMode by remember { mutableStateOf(false) }
//    var fontSize by remember { mutableStateOf(16f) }
//    var fontFamily by remember { mutableStateOf(FontFamily.Default) }
//
//    LaunchedEffect(noteToEdit) {
//        noteToEdit?.let {
//            textFieldValue = TextFieldValue(it.content)
//        }
//    }
//
//    val imagePickerLauncher = rememberLauncherForActivityResult(
//        contract = ActivityResultContracts.GetContent()
//    ) { uri: Uri? ->
//        uri?.let {
//            val imageMarkdown = "\n\n![]($uri)\n\n"
//            val oldText = textFieldValue.text
//            val cursor = textFieldValue.selection.start
//            val newText = oldText.substring(0, cursor) + imageMarkdown + oldText.substring(cursor)
//            textFieldValue = TextFieldValue(
//                text = newText,
//                selection = TextRange(cursor + imageMarkdown.length)
//            )
//        }
//    }
//
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = { Text("Smart Note") },
//                actions = {
//                    Row {
//                        Text(
//                            if (isPreviewMode) "Edit" else "Preview",
//                            modifier = Modifier
//                                .padding(horizontal = 12.dp)
//                                .pointerInput(Unit) {
//                                    detectTapGestures { isPreviewMode = !isPreviewMode }
//                                }
//                        )
//                    }
//                }
//            )
//        },
//        floatingActionButton = {
//            FloatingActionButton(
//                onClick = {
//                    val content = textFieldValue.text
//                    if (content.isNotBlank()) {
//                        if (noteId != null) {
//                            viewModel.updateNote(noteId, content = content, title = "") // title is embedded
//                        } else {
//                            viewModel.addNote(content = content, title = "")
//                        }
//                        navController.popBackStack()
//                    }
//                }
//            ) {
//                Text("✓")
//            }
//        }
//    ) { padding ->
//        Column(
//            modifier = Modifier
//                .padding(padding)
//                .padding(16.dp)
//                .verticalScroll(rememberScrollState())
//        ) {
//            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
//                FontSizePicker(fontSize) { fontSize = it }
//                FontFamilyPicker(fontFamily) { fontFamily = it as SystemFontFamily }
//            }
//
//            if (isPreviewMode) {
//                RenderNoteContentWithImages(
//                    markdown = textFieldValue.text,
//                    fontSize = fontSize,
//                    fontFamily = fontFamily
//                )
//            } else {
//                Box(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .heightIn(min = 400.dp)
//                        .background(MaterialTheme.colorScheme.surface, MaterialTheme.shapes.medium)
//                        .padding(12.dp)
//                ) {
//                    BasicTextField(
//                        value = textFieldValue,
//                        onValueChange = { textFieldValue = it },
//                        textStyle = TextStyle(fontSize = fontSize.sp, fontFamily = fontFamily),
//                        modifier = Modifier.fillMaxSize()
//                    )
//                }
//
//                Spacer(modifier = Modifier.height(16.dp))
//                Button(
//                    onClick = { imagePickerLauncher.launch("image/*") },
//                    modifier = Modifier.fillMaxWidth()
//                ) {
//                    Text("Insert Image at Cursor")
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun RenderNoteContentWithImages(
//    markdown: String,
//    fontSize: Float,
//    fontFamily: FontFamily
//) {
//    val lines = markdown.split("\n")
//    Column {
//        for (line in lines) {
//            if (line.startsWith("![](") && line.endsWith(")")) {
//                val uri = line.substringAfter("![](").substringBeforeLast(")")
//                Image(
//                    painter = rememberAsyncImagePainter(Uri.parse(uri)),
//                    contentDescription = "Inline image",
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .heightIn(min = 180.dp)
//                        .padding(vertical = 8.dp)
//                )
//            } else {
//                Text(
//                    text = line,
//                    style = TextStyle(
//                        fontSize = fontSize.sp,
//                        fontFamily = fontFamily,
//                        color = Color.Unspecified
//                    ),
//                    modifier = Modifier.padding(vertical = 2.dp)
//                )
//            }
//        }
//    }
//}
//
//@Composable
//fun FontSizePicker(currentSize: Float, onSizeChange: (Float) -> Unit) {
//    Row(verticalAlignment = Alignment.CenterVertically) {
//        Text("Size:", modifier = Modifier.padding(end = 4.dp))
//        Slider(
//            value = currentSize,
//            onValueChange = { onSizeChange(it) },
//            valueRange = 12f..30f,
//            steps = 4,
//            modifier = Modifier.width(120.dp)
//        )
//    }
//}
//
//@Composable
//fun FontFamilyPicker(currentFamily: FontFamily, onFamilyChange: (FontFamily) -> Unit) {
//    var expanded by remember { mutableStateOf(false) }
//    val options = listOf("Default", "Serif", "Monospace")
//    val selectedText = when (currentFamily) {
//        FontFamily.Monospace -> "Monospace"
//        FontFamily.Serif -> "Serif"
//        else -> "Default"
//    }
//
//    Box {
//        TextButton(onClick = { expanded = true }) {
//            Text("Font: $selectedText")
//        }
//        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
//            options.forEach { option ->
//                DropdownMenuItem(
//                    text = { Text(option) },
//                    onClick = {
//                        onFamilyChange(
//                            when (option) {
//                                "Monospace" -> FontFamily.Monospace
//                                "Serif" -> FontFamily.Serif
//                                else -> FontFamily.Default
//                            }
//                        )
//                        expanded = false
//                    }
//                )
//            }
//        }
//    }
//}
//
package com.example.smartnotes.presentation.addNote

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.SystemFontFamily
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.smartnotes.presentation.viewmodel.NoteViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNoteScreen(
    navController: NavController,
    noteId: Int? = null,
    viewModel: NoteViewModel = hiltViewModel()
) {
    val noteToEdit by viewModel.getNoteById(noteId).collectAsState(initial = null)

    var textFieldValue by remember {
        mutableStateOf(TextFieldValue("", TextRange(0)))
    }
    var isPreviewMode by remember { mutableStateOf(false) }
    var fontSize by remember { mutableStateOf(16f) }
    var fontFamily by remember { mutableStateOf(FontFamily.Default) }
    var fontColor by remember { mutableStateOf(Color.Black) }

    // Load existing note
    LaunchedEffect(noteToEdit) {
        noteToEdit?.let {
            textFieldValue = TextFieldValue(it.content, TextRange(0))
        }
    }

    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            val imageMarkdown = "\n\n![]($uri)\n\n"
            val oldText = textFieldValue.text
            val cursor = textFieldValue.selection.start
            val newText = oldText.substring(0, cursor) + imageMarkdown + oldText.substring(cursor)
            textFieldValue = TextFieldValue(
                text = newText,
                selection = TextRange(cursor + imageMarkdown.length)
            )
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Smart Note") },
                actions = {
                    IconButton(onClick = {
                        imagePickerLauncher.launch("image/*")
                    }) {
                        Icon(Icons.Default.Edit, contentDescription = "Insert Image")
                    }
                    Text(
                        if (isPreviewMode) "Edit" else "Preview",
                        modifier = Modifier
                            .padding(horizontal = 12.dp)
                            .pointerInput(Unit) {
                                detectTapGestures {
                                    isPreviewMode = !isPreviewMode
                                }
                            }
                    )
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                val content = textFieldValue.text
                if (content.isNotBlank()) {
                    if (noteId != null) {
                        viewModel.updateNote(noteId, content = content, title = "")
                    } else {
                        viewModel.addNote(content = content, title = "")
                    }
                    navController.popBackStack()
                }
            }) {
                Text("✓")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                FontSizePicker(fontSize) { fontSize = it }
                FontFamilyPicker(fontFamily) { fontFamily = it as SystemFontFamily }
                FontColorPicker(fontColor) { fontColor = it }
            }

            Spacer(modifier = Modifier.height(12.dp))

            if (isPreviewMode) {
                RenderNoteContentWithImages(
                    markdown = textFieldValue.text,
                    fontSize = fontSize,
                    fontFamily = fontFamily,
                    fontColor = fontColor
                )
            } else {
                val focusRequester = remember { FocusRequester() }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 400.dp)
                        .background(MaterialTheme.colorScheme.surface, MaterialTheme.shapes.medium)
                        .padding(12.dp)
                        .clickable {
                            focusRequester.requestFocus()
                        }
                ) {
                    BasicTextField(
                        value = textFieldValue,
                        onValueChange = { textFieldValue = it },
                        textStyle = TextStyle(
                            fontSize = fontSize.sp,
                            fontFamily = fontFamily,
                            color = fontColor
                        ),
                        cursorBrush = SolidColor(fontColor),

                        modifier = Modifier.fillMaxSize().focusRequester(focusRequester)

                    )
                }
            }
        }
    }
}

@Composable
fun RenderNoteContentWithImages(
    markdown: String,
    fontSize: Float,
    fontFamily: FontFamily,
    fontColor: Color
) {
    val lines = markdown.split("\n")
    Column {
        for (line in lines) {
            if (line.startsWith("![](") && line.endsWith(")")) {
                val uri = line.substringAfter("![](").substringBeforeLast(")")
                Image(
                    painter = rememberAsyncImagePainter(Uri.parse(uri)),
                    contentDescription = "Inline image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 180.dp)
                        .padding(vertical = 8.dp)
                )
            } else {
                Text(
                    text = line,
                    style = TextStyle(
                        fontSize = fontSize.sp,
                        fontFamily = fontFamily,
                        color = fontColor
                    ),
                    modifier = Modifier.padding(vertical = 2.dp)
                )
            }
        }
    }
}

@Composable
fun FontSizePicker(currentSize: Float, onSizeChange: (Float) -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text("Size:", modifier = Modifier.padding(end = 4.dp))
        Slider(
            value = currentSize,
            onValueChange = { onSizeChange(it) },
            valueRange = 12f..30f,
            steps = 6,
            modifier = Modifier.width(120.dp)
        )
    }
}

@Composable
fun FontFamilyPicker(currentFamily: FontFamily, onFamilyChange: (FontFamily) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    val options = listOf("Default", "Serif", "Monospace")
    val selectedText = when (currentFamily) {
        FontFamily.Monospace -> "Monospace"
        FontFamily.Serif -> "Serif"
        else -> "Default"
    }

    Box {
        TextButton(onClick = { expanded = true }) {
            Text("Font: $selectedText")
        }
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {
                        onFamilyChange(
                            when (option) {
                                "Monospace" -> FontFamily.Monospace
                                "Serif" -> FontFamily.Serif
                                else -> FontFamily.Default
                            }
                        )
                        expanded = false
                    }
                )
            }
        }
    }
}

@Composable
fun FontColorPicker(currentColor: Color, onColorChange: (Color) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    val colors = listOf(Color.Black, Color.Red, Color.Blue, Color.Green, Color.White)

    Box {
        TextButton(onClick = { expanded = true }) {
            Text("Color")
        }
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            colors.forEach { color ->
                DropdownMenuItem(
                    text = {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                Modifier
                                    .size(16.dp)
                                    .background(color)
                            )
                            Spacer(Modifier.width(8.dp))
                            Text(color.toString())
                        }
                    },
                    onClick = {
                        onColorChange(color)
                        expanded = false
                    }
                )
            }
        }
    }
}
