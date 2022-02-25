package com.ifreedomer.mockk.note.use_case

import com.ifreedomer.mockk.note.db.NoteRepository
import com.ifreedomer.mockk.note.entity.NoteEntity

class DeleteNote(
    private val repository: NoteRepository
) {

    suspend operator fun invoke(note: NoteEntity) {
        repository.deleteNote(note)
    }
}