package com.ifreedomer.mockk.note.use_case
import com.ifreedomer.mockk.note.db.NoteRepository
import com.ifreedomer.mockk.note.entity.NoteEntity
import com.ifreedomer.mockk.note.util.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetNotes(
    private val repository: NoteRepository
) {
    operator fun invoke(
        noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending)
    ): Flow<List<NoteEntity>> {
        return repository.getNotes().map { notes ->
            when(noteOrder.orderType) {
                is OrderType.Ascending -> {
                    when(noteOrder) {
                        is NoteOrder.Title -> notes.sortedBy { it.title.lowercase() }
                        is NoteOrder.Date -> notes.sortedBy { it.time }
                        is NoteOrder.Color -> notes.sortedByDescending { it.time }
                    }
                }
                is OrderType.Descending -> {
                    when(noteOrder) {
                        is NoteOrder.Title -> notes.sortedByDescending { it.title.lowercase() }
                        is NoteOrder.Date -> notes.sortedByDescending { it.time }
                        is NoteOrder.Color -> notes.sortedByDescending { it.time }
                    }
                }
            }
        }
    }
}