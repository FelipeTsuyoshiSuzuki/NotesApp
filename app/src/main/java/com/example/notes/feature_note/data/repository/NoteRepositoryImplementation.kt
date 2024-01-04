package com.example.notes.feature_note.data.repository

import com.example.notes.feature_note.data.data_source.NoteDao
import com.example.notes.feature_note.domain.model.Note
import com.example.notes.feature_note.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

class NoteRepositoryImplementation(
    private val dao: NoteDao
) : NoteRepository {
    override fun getNotes(): Flow<List<Note>> {
        return dao.getNotes()
    }

    override suspend fun getNoteByid(id: Int): Note? {
        return dao.getNoteByid(id)
    }

    override suspend fun insertNote(note: Note) {
        return insertNote(note)
    }

    override suspend fun deleteNote(note: Note) {
        deleteNote(note)
    }

}