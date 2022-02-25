package com.ifreedomer.mockk.note.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.ifreedomer.mockk.note.entity.NoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Query("SELECT * FROM note")
    fun getNotes(): Flow<List<NoteEntity>>

    @Query("SELECT * FROM note WHERE id = :id")
    suspend fun getNoteById(id: Long): NoteEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: NoteEntity)

    @Delete
    suspend fun deleteNote(note: NoteEntity)
}