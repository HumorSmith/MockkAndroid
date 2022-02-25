package com.ifreedomer.mockk.note.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ifreedomer.mockk.note.entity.NoteEntity

@Database(entities = [NoteEntity::class], version = 1)
abstract class NoteDataBase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}