package com.example.notes.feature_note.data.data_source

import android.content.Context
import androidx.compose.ui.graphics.toArgb
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.notes.feature_note.domain.model.Note
import com.example.notes.ui.theme.RedOrange
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class NoteDaoTest {

    private lateinit var db: NoteDatabase
    private lateinit var dao: NoteDao
    private lateinit var context: Context

    @Before
    fun setup() {
        context = ApplicationProvider.getApplicationContext()
        db = Room.inMemoryDatabaseBuilder(
            context,
            NoteDatabase::class.java
        ).allowMainThreadQueries()
            .build()
        dao = db.noteDao
    }

    @After
    fun teardown() {
        db.close()
    }

    @Test
    fun getNoteById() = runTest {
        val noteItem1 = Note(id = 1, "title", "content", 1705705241637, RedOrange.toArgb())
        val noteItem2 = Note(id = 2, "title", "content", 1705705241637, RedOrange.toArgb())
        val noteItem3 = Note(id = 3, "title", "content", 1705705241637, RedOrange.toArgb())
        val noteItem4 = Note(id = 4, "title", "content", 1705705241637, RedOrange.toArgb())
        dao.insertNote(noteItem1)
        dao.insertNote(noteItem2)
        dao.insertNote(noteItem3)
        dao.insertNote(noteItem4)

        val selectNote = dao.getNoteByid(2)
        assertThat(selectNote).isEqualTo(noteItem2)
    }

    @Test
    fun queryAllNotes() = runTest {
        val noteItem1 = Note(id = 1, "title", "content", 1705705241637, RedOrange.toArgb())
        val noteItem2 = Note(id = 2, "title", "content", 1705705241637, RedOrange.toArgb())
        val noteItem3 = Note(id = 3, "title", "content", 1705705241637, RedOrange.toArgb())
        val noteItem4 = Note(id = 4, "title", "content", 1705705241637, RedOrange.toArgb())
        dao.insertNote(noteItem1)
        dao.insertNote(noteItem2)
        dao.insertNote(noteItem3)
        dao.insertNote(noteItem4)

        val allNotes = dao.getNotes().first()

        assertThat(allNotes.size).isEqualTo(4)
        assertThat(allNotes).contains(noteItem1)
        assertThat(allNotes).contains(noteItem2)
    }

    @Test
    fun insertNoteItem() = runTest {
        val noteItem = Note(id = 1, "title", "content", 1705705241637, RedOrange.toArgb())
        dao.insertNote(noteItem)

        val allNotes = dao.getNotes().first()
        assertThat(noteItem).isIn(allNotes)
    }

    @Test
    fun deleteNoteItem() = runTest {
        val noteItem1 = Note(id = 1, "title", "content", 1705705241637, RedOrange.toArgb())
        val noteItem2 = Note(id = 2, "title", "content", 1705705241637, RedOrange.toArgb())
        val noteItem3 = Note(id = 3, "title", "content", 1705705241637, RedOrange.toArgb())
        val noteItem4 = Note(id = 4, "title", "content", 1705705241637, RedOrange.toArgb())

        dao.insertNote(noteItem1)
        dao.insertNote(noteItem2)
        dao.insertNote(noteItem3)
        dao.insertNote(noteItem4)
        dao.deleteNote(noteItem2)

        val allNotes = dao.getNotes().first()
        assertThat(allNotes.size).isEqualTo(3)
        assertThat(noteItem2).isNotIn(allNotes)
    }
}