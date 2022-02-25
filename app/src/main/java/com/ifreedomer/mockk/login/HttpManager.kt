package com.ifredomer.studyunittest

import com.ifredomer.studyunittest.api.LoginApi
import com.ifredomer.studyunittest.model.UserInfo
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object HttpManager {
    var client = OkHttpClient()

    var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("http://www.chinesepast.com/")
        .addConverterFactory(MoshiConverterFactory.create())
        .client(client)
        .build()



    suspend fun login(userName:String, password:String):UserInfo{
        val loginApi = retrofit.create(LoginApi::class.java)
        return loginApi.login(userName,password)
    }
}