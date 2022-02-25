package com.ifreedomer.mockk.note.entity

import androidx.room.Entity

@Entity(tableName = "note")
data class NoteEntity(
    var id:Long,
    var title: String,
    var content: String,
    var time: Long,
    var color:Int
)