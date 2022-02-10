package com.ifredomer.studyunittest.api

import com.ifredomer.studyunittest.model.UserInfo
import retrofit2.http.GET
import retrofit2.http.Query

interface LoginApi {
    @GET("/study/login/login.json")
    suspend fun login(@Query("userName") userName: String, @Query("password") password: String):UserInfo
}