package com.ifreedomer.mockk.note.db

import com.ifreedomer.mockk.MockApplication
import com.ifreedomer.mockk.note.entity.NoteEntity
import kotlinx.coroutines.flow.Flow

class NoteLocalRepository : NoteRepository {
    override fun getNotes(): Flow<List<NoteEntity>> {
        return MockApplication.DATABASE.noteDao().getNotes()
    }

    override suspend fun getNoteById(id: Long): NoteEntity? {
        return MockApplication.DATABASE.noteDao().getNoteById(id)
    }

    override suspend fun insertNote(note: NoteEntity) {
        MockApplication.DATABASE.noteDao().insertNote(note)
    }

    override suspend fun deleteNote(note: NoteEntity) {
        MockApplication.DATABASE.noteDao().deleteNote(note)
    }
}