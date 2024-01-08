package com.example.notes.feature_note.presentation.utils

sealed class Screens(val route: String) {
    data object NotesScreen: Screens("note_screen")
    data object AddEditNoteScreen: Screens("add_edit_note_screen")
}