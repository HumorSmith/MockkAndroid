package com.ifreedomer.mockk

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.ifreedomer.mockk.login.LoginActivity
import com.ifreedomer.mockk.note.NoteActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.login_btn).setOnClickListener {
            startActivity(Intent(this,LoginActivity::class.java))
        }
        findViewById<Button>(R.id.database_btn).setOnClickListener {
            startActivity(Intent(this,NoteActivity::class.java))
        }
    }
}