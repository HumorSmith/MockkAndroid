package com.ifreedomer.mockk.note

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ifreedomer.mockk.R
import com.ifreedomer.mockk.note.adapter.NoteAdapter
import com.ifreedomer.mockk.note.db.NoteLocalRepository
import com.ifreedomer.mockk.note.entity.NoteEntity
import com.ifreedomer.mockk.note.use_case.AddNote
import com.ifreedomer.mockk.note.use_case.GetNotes
import com.ifreedomer.mockk.note.util.NoteOrder
import com.ifreedomer.mockk.note.util.OrderType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NoteActivity : AppCompatActivity() {
    var orderType: OrderType = OrderType.Ascending
    var noteOrder: NoteOrder = NoteOrder.Title(orderType)
    val getNotes = GetNotes(NoteLocalRepository())
    var adapter: NoteAdapter? = null
    var data = mutableListOf<NoteEntity>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_base)
        addMockData()
        findViewById<Button>(R.id.color_btn).setOnClickListener {
            noteOrder = NoteOrder.Color(orderType)
            refreshData()
        }
        findViewById<Button>(R.id.title_btn).setOnClickListener {
            noteOrder = NoteOrder.Title(orderType)
            refreshData()
        }
        findViewById<Button>(R.id.time_btn).setOnClickListener {
            noteOrder = NoteOrder.Date(orderType)
            refreshData()
        }

        findViewById<Button>(R.id.desc_btn).setOnClickListener {
            orderType = OrderType.Descending
            noteOrder = noteOrder.copy(orderType)
            refreshData()
        }
        findViewById<Button>(R.id.asc_btn).setOnClickListener {
            orderType = OrderType.Ascending
            noteOrder = noteOrder.copy(orderType)
            refreshData()
        }

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        adapter = NoteAdapter(R.layout.item_note, data)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        noteOrder = NoteOrder.Date(orderType)
        refreshData()

    }

    private fun addMockData() {
        val sharedPreferences = getSharedPreferences("mock", Context.MODE_PRIVATE)
        val boolean = sharedPreferences.getBoolean("isFirst",false)
        if (boolean){
            return
        }
        sharedPreferences.edit().putBoolean("isFirst",true).apply()
        MainScope().launch {
            withContext(Dispatchers.IO) {
                AddNote(NoteLocalRepository()).invoke(
                    NoteEntity(
                        1, "a", "aaaa", System.currentTimeMillis(),
                        Color.RED
                    )
                )

                AddNote(NoteLocalRepository()).invoke(
                    NoteEntity(
                        2, "b", "bbbb", System.currentTimeMillis() + 1,
                        Color.BLUE
                    )
                )

                AddNote(NoteLocalRepository()).invoke(
                    NoteEntity(
                        3, "c", "cccc", System.currentTimeMillis() + 2,
                        Color.DKGRAY
                    )
                )
            }

            noteOrder = NoteOrder.Date(orderType)
            refreshData()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun refreshData() {
        val notes = getNotes(noteOrder)
        data.clear()
        MainScope().launch {
            val visit = withContext(Dispatchers.IO) {
                notes.first()
            }
            Log.d("refreshData","visit = $visit  adapter = $adapter")
            data.addAll(visit)
            adapter?.notifyDataSetChanged()
        }
    }
}