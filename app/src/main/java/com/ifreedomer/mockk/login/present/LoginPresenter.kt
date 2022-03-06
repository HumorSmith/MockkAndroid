package com.ifreedomer.mockk.login.present

import android.text.TextUtils
import com.ifreedomer.mockk.login.HttpManager
import com.ifredomer.studyunittest.model.UserInfo

class LoginPresenter {
    fun login(userName: String, password: String): UserInfo? {
        if (userName.isEmpty()) {
            return null
        }
        if (password.isEmpty()) {
            return null
        }
        return UserInfo(userName, password)
    }


    fun loginAndroid(userName: String, password: String): UserInfo? {
        if (TextUtils.isEmpty(userName)) {
            return null
        }
        if (TextUtils.isEmpty(userName)) {
            return null
        }
        return UserInfo(userName, password)
    }

    suspend fun loginAndroidNetwork(userName: String, password: String): UserInfo? {
        if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(password)) {
            return null
        }
        return HttpManager.login(userName, password)
    }

}