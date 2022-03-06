package com.ifreedomer.mockk

import androidx.test.espresso.Espresso.closeSoftKeyboard
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ifreedomer.mockk.login.LoginActivity
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.espresso.matcher.ViewMatchers.withTagValue
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import com.ifreedomer.mockk.util.SimpleIdlingResource
import org.hamcrest.Matchers.`is`
import org.junit.Before
import org.junit.After
import org.junit.Rule


@RunWith(AndroidJUnit4::class)
@LargeTest
class TestLoginActivity {
    var idleResource: SimpleIdlingResource? = null


    @get:Rule
    var activityScenario = ActivityScenarioRule(LoginActivity::class.java)

    @Before
    fun setup() {
        activityScenario.scenario.onActivity {
            val activity = it as LoginActivity
            idleResource = activity.idleResource
            // To prove that the test fails, omit this call:
            IdlingRegistry.getInstance().register(activity.idleResource)
        }
    }

    @Test
    fun testLoginSuccess() {
        //withId withText  withResourceName  withTagKey  withTagValue
        onView(withId(R.id.username_et)).perform(click()).perform(typeText("Engineer"))
        onView(withId(R.id.passowrd_et)).perform(click()).perform(typeText("Engineer"))
        //一定要收起键盘，否则会找不到登录按钮
        closeSoftKeyboard()
        onView(withId(R.id.login_btn)).perform(click())
        onView(withTagValue(`is`(LoginActivity.LOGIN_SUCCESS_TAG))).check(matches(isDisplayed()))
    }

    @Test
    fun testLoginFailed() {
        onView(withId(R.id.username_et)).perform(click()).perform(typeText(""))
        onView(withId(R.id.passowrd_et)).perform(click()).perform(typeText("Engineer"))
        closeSoftKeyboard()
        //一定要收起键盘，否则会找不到登录按钮
        onView(withId(R.id.login_btn)).perform(click())
        onView(withTagValue(`is`(LoginActivity.LOGIN_FAILED_TAG))).check(matches(isDisplayed()))
    }

    @After
    fun release() {
        if (idleResource != null) {
            IdlingRegistry.getInstance().unregister(idleResource)
        }
    }

}