package com.example.notes.di

import android.app.Application
import androidx.room.Room
import com.example.notes.feature_note.data.data_source.NoteDatabase
import com.example.notes.feature_note.data.data_source.NoteDatabase.Companion.DATABASE_NAME
import com.example.notes.feature_note.data.repository.NoteRepositoryImplementation
import com.example.notes.feature_note.domain.repository.NoteRepository
import com.example.notes.feature_note.domain.use_case.AddNoteUseCase
import com.example.notes.feature_note.domain.use_case.DeleteNoteUseCase
import com.example.notes.feature_note.domain.use_case.GetNoteUseCase
import com.example.notes.feature_note.domain.use_case.GetNotesUseCase
import com.example.notes.feature_note.domain.use_case.NoteUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNotesDatabase(app: Application): NoteDatabase {
        return Room.databaseBuilder(
            app,
            NoteDatabase::class.java,
            DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideNoteRepository(db: NoteDatabase): NoteRepository {
        return NoteRepositoryImplementation(db.noteDao)
    }

    @Provides
    @Singleton
    fun provideNoteUseCases(repository: NoteRepository): NoteUseCases {
        return NoteUseCases(
            getNotes = GetNotesUseCase(repository),
            deleteNote = DeleteNoteUseCase(repository),
            addNote = AddNoteUseCase(repository),
            getNote = GetNoteUseCase(repository)
        )
    }
}