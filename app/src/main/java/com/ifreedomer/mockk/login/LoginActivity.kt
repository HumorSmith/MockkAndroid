package com.ifreedomer.mockk.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.ifreedomer.mockk.R
import com.ifreedomer.mockk.login.present.LoginPresenter
import kotlinx.coroutines.*

class LoginActivity : AppCompatActivity() {
    companion object {
        const val TAG = "MainActivity"
    }
    private val presenter = LoginPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val loginBtn = findViewById<Button>(R.id.login_btn)
        val usernameEt = findViewById<EditText>(R.id.username_et)
        val passwordEt = findViewById<EditText>(R.id.passowrd_et)
        loginBtn.setOnClickListener {
            MainScope().launch {
                withContext(Dispatchers.IO) {
                    val userinfo = presenter.login("123","134")
                    Log.d(TAG, "userinfo = $userinfo")
                }
            }
        }
    }
}