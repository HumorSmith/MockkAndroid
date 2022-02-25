package com.ifreedomer.mockk.fake

import com.ifreedomer.mockk.note.db.NoteRepository
import com.ifreedomer.mockk.note.entity.NoteEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeNoteRepository : NoteRepository {

    private val notes = mutableListOf<NoteEntity>()

    override fun getNotes(): Flow<List<NoteEntity>> {
        return flow { emit(notes) }
    }

    override suspend fun getNoteById(id: Long): NoteEntity? {
        return notes.find { it.id == id }
    }

    override suspend fun insertNote(note: NoteEntity) {
        notes.add(note)
    }

    override suspend fun deleteNote(note: NoteEntity) {
        notes.remove(note)
    }
}