package com.ifreedomer.mockk.note.viewmodel

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.ifreedomer.mockk.MockApplication
import com.ifreedomer.mockk.note.db.NoteDataBase
import com.ifreedomer.mockk.note.db.NoteLocalRepository
import com.ifreedomer.mockk.note.db.NoteRepository
import com.ifreedomer.mockk.note.entity.NoteEntity

class NoteViewModel : ViewModel() {
    var repository:NoteRepository? = null
    fun setRepo(repo: NoteRepository){
        repository = repo
    }

    fun insertNote(noteEntity: NoteEntity){
    }



}