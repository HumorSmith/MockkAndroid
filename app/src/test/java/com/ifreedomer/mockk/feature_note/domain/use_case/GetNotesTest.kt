package com.ifreedomer.mockk.feature_note.domain.use_case

import com.google.common.truth.Truth.assertThat
import com.ifreedomer.mockk.fake.FakeNoteRepository
import com.ifreedomer.mockk.note.entity.NoteEntity
import com.ifreedomer.mockk.note.use_case.GetNotes
import com.ifreedomer.mockk.note.util.NoteOrder
import com.ifreedomer.mockk.note.util.OrderType
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetNotesTest {
    private lateinit var getNotes: GetNotes
    private lateinit var fakeRepository: FakeNoteRepository
    @Before
    fun setUp() {
        fakeRepository = FakeNoteRepository()
        getNotes = GetNotes(fakeRepository)

        val notesToInsert = mutableListOf<NoteEntity>()
        ('a'..'z').forEachIndexed { index, c ->
            notesToInsert.add(
                NoteEntity(
                    title = c.toString(),
                    content = c.toString(),
                    time = index.toLong(),
                    color = index,
                    id = System.currentTimeMillis()
                )
            )
        }
        notesToInsert.shuffle()
        runBlocking {
            notesToInsert.forEach { fakeRepository.insertNote(it) }
        }
    }

    @Test
    fun `根据Title升序排列`() = runBlocking {
        val notes = getNotes(NoteOrder.Title(OrderType.Ascending)).first()

        for (i in 0..notes.size - 2) {
            assertThat(notes[i].title).isLessThan(notes[i + 1].title)
        }
    }

    @Test
    fun `根据Title降序排列`() = runBlocking {
        val notes = getNotes(NoteOrder.Title(OrderType.Descending)).first()

        for (i in 0..notes.size - 2) {
            assertThat(notes[i].title).isGreaterThan(notes[i + 1].title)
        }
    }

    @Test
    fun `根据时间升序排列`() = runBlocking {
        val notes = getNotes(NoteOrder.Date(OrderType.Ascending)).first()

        for (i in 0..notes.size - 2) {
            assertThat(notes[i].time).isLessThan(notes[i + 1].time)
        }
    }

    @Test
    fun `根据时间降序排列`() = runBlocking {
        val notes = getNotes(NoteOrder.Date(OrderType.Descending)).first()

        for (i in 0..notes.size - 2) {
            assertThat(notes[i].time).isGreaterThan(notes[i + 1].time)
        }
    }

    @Test
    fun `根据颜色升序排列`() = runBlocking {
        val notes = getNotes(NoteOrder.Color(OrderType.Ascending)).first()

        for (i in 0..notes.size - 2) {
            assertThat(notes[i].color).isLessThan(notes[i + 1].color)
        }
    }

    @Test
    fun `根据颜色降序排列`() = runBlocking {
        val notes = getNotes(NoteOrder.Color(OrderType.Descending)).first()

        for (i in 0..notes.size - 2) {
            assertThat(notes[i].color).isGreaterThan(notes[i + 1].color)
        }
    }
}