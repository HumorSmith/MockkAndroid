package com.ifreedomer.mockk.fake

import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.ResponseBody.Companion.toResponseBody

class FakeLoginInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var responseString = "{\"userName\":\"haha\",\"password\":\"1234\"}"
        return Response.Builder()
            .code(200)
            .message(responseString)
            .request(chain.request())
            .protocol(Protocol.HTTP_1_0)
            .body(responseString.toResponseBody(contentType = "application/json;charset=utf-8".toMediaType()))
            .addHeader("content-type", "application/json")
            .build()
    }
}