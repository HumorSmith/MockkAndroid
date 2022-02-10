package com.ifreedomer.mockk

import com.ifreedomer.mockk.present.LoginPresenter
import android.text.TextUtils
import com.ifredomer.studyunittest.HttpManager
import com.ifredomer.studyunittest.model.UserInfo
import com.ifreedomer.mockk.fake.FakeLoginInterceptor
import com.ifreedomer.mockk.text.MineTextUtil
import io.mockk.*
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.junit.Test

import org.junit.Before
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class LoginPresenterTest {
    @Before
    fun setup() {
        MockKAnnotations.init(this)
        mockTextUtils()
    }

    private fun mockServerConfig(interceptor: Interceptor) {
        var client = OkHttpClient.Builder().addInterceptor(interceptor).build()
        val retrofit = Retrofit.Builder().addConverterFactory(MoshiConverterFactory.create())
            .baseUrl("http://localhost")
            .client(client).build()
        HttpManager.retrofit = retrofit
    }

    private fun mockTextUtils() {
        mockkStatic(TextUtils::isEmpty)
        every { TextUtils.isEmpty(any()) } answers {
            println("====static called====")
            arg<String>(0).isEmpty()
        }
    }

    @Test
    fun `test mock userinfo`() {
        val userInfo = mockk<UserInfo>("model.UserInfo")
        every { userInfo.userName } returns "hello world"
        println("===${userInfo.userName}===")
    }

    @Test
    fun `test mock static`() {
        mockkStatic(MineTextUtil::class)
        every { MineTextUtil.isEmpty(any()) } answers {
            println("static called")
            arg<String>(0).isEmpty()
        }
        val isEmpty = MineTextUtil.isEmpty("")
        println("isEmpty = $isEmpty")
        unmockkStatic(MineTextUtil::class)
    }

    @Test
    fun `test login`() {
        val presenter = LoginPresenter()
        assert(presenter.login("", "") == null)
        assert(presenter.login("123", "") == null)
        assert(presenter.login("", "123") == null)
        assert(presenter.login("123", "1234") != null)
    }

    @Test
    fun `test login for android`() {
        mockkStatic(MineTextUtil::isEmpty)
        every { MineTextUtil.isEmpty(any()) } answers {
            println("====static called====")
            arg<String>(0).isEmpty()
        }
        val presenter = LoginPresenter()
        val user = presenter.loginAndroid("123", "1234")
        assert(user != null)
        unmockkStatic(MineTextUtil::class)
    }

    @Test
    fun `test login for android network`(): Unit = runBlocking {
        val presenter = LoginPresenter()
        assert(presenter.loginAndroidNetwork("", "") == null)
        assert(presenter.loginAndroidNetwork("123", "") == null)
        assert(presenter.loginAndroidNetwork("", "123") == null)
        assert(presenter.loginAndroidNetwork("123", "1234") != null)
    }

    @Test
    fun `test login for mock network`(): Unit = runBlocking {
        mockServerConfig(FakeLoginInterceptor())
        val presenter = LoginPresenter()
        val userInfo = presenter.loginAndroidNetwork("123", "123")
        print("$userInfo")
    }


}