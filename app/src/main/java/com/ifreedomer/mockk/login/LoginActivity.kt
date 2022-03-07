package com.ifreedomer.mockk.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.annotation.VisibleForTesting
import com.ifreedomer.mockk.R
import com.ifreedomer.mockk.login.present.LoginPresenter
import com.ifreedomer.mockk.util.SimpleIdlingResource
import kotlinx.coroutines.*

class LoginActivity : AppCompatActivity() {
    companion object {
        const val TAG = "MainActivity"
        const val LOGIN_SUCCESS_TAG = "login_success"
        const val LOGIN_FAILED_TAG = "login_failed"
    }
    @VisibleForTesting
    var idleResource: SimpleIdlingResource = SimpleIdlingResource()
    private val presenter = LoginPresenter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val loginBtn = findViewById<Button>(R.id.login_btn)
        val usernameEt = findViewById<EditText>(R.id.username_et)
        val passwordEt = findViewById<EditText>(R.id.passowrd_et)
        loginBtn.setOnClickListener {
            idleResource.setIdleState(false)
            MainScope().launch {
                val userInfo = withContext(Dispatchers.IO) {
                    presenter.loginAndroidNetwork(
                        usernameEt.text.toString(),
                        passwordEt.text.toString()
                    )
                }
                idleResource.setIdleState(true)
                loginBtn.tag = LOGIN_SUCCESS_TAG
                Log.d(TAG, " userinfo = $userInfo")
                if (userInfo != null) {
                    loginBtn.tag = LOGIN_SUCCESS_TAG
                } else {
                    loginBtn.tag = LOGIN_FAILED_TAG
                }
            }
        }
    }
}