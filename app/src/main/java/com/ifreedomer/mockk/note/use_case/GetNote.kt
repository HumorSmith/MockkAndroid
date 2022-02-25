package com.ifreedomer.mockk.note.use_case

import com.ifreedomer.mockk.note.db.NoteRepository
import com.ifreedomer.mockk.note.entity.NoteEntity

class GetNote(
    private val repository: NoteRepository
) {

    suspend operator fun invoke(id: Long): NoteEntity? {
        return repository.getNoteById(id)
    }
}