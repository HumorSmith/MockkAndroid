package com.ifreedomer.mockk

import android.app.Application
import androidx.room.Room
import com.ifreedomer.mockk.note.db.NoteDataBase

class MockApplication : Application() {
    companion object {
        lateinit var DATABASE: NoteDataBase
    }

    override fun onCreate() {
        super.onCreate()
        DATABASE = Room.databaseBuilder(
            applicationContext,
            NoteDataBase::class.java, "note.db"
        ).build()
    }
}