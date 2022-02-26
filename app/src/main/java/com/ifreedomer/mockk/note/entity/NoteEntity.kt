package com.ifreedomer.mockk.note.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note")
data class NoteEntity(
    @PrimaryKey var id:Long,
    var title: String,
    var content: String,
    var time: Long,
    var color:Int
)