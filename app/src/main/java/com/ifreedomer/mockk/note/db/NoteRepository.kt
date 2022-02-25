package com.ifreedomer.mockk.note.db
import com.ifreedomer.mockk.note.entity.NoteEntity
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    fun getNotes(): Flow<List<NoteEntity>>

    suspend fun getNoteById(id: Long): NoteEntity?

    suspend fun insertNote(note: NoteEntity)

    suspend fun deleteNote(note: NoteEntity)
}