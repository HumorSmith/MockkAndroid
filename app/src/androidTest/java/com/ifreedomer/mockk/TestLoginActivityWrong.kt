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
class TestLoginActivityWrong {
    @get:Rule
    var activityScenario = ActivityScenarioRule(LoginActivity::class.java)

    @Test
    fun testLoginSuccessWrong1() {
        //withId withText  withResourceName  withTagKey  withTagValue
        onView(withId(R.id.username_et)).perform(click()).perform(typeText("Engineer"))
        onView(withId(R.id.passowrd_et)).perform(click()).perform(typeText("Engineer"))
        onView(withId(R.id.login_btn)).perform(click())
        onView(withTagValue(`is`(LoginActivity.LOGIN_SUCCESS_TAG))).check(matches(isDisplayed()))
    }


    @Test
    fun testLoginSuccessWrong2() {//偶现case错误，因为未处理异步逻辑
        //withId withText  withResourceName  withTagKey  withTagValue
        onView(withId(R.id.username_et)).perform(click()).perform(typeText("Engineer"))
        onView(withId(R.id.passowrd_et)).perform(click()).perform(typeText("Engineer"))
        closeSoftKeyboard()
        onView(withId(R.id.login_btn)).perform(click())
        onView(withTagValue(`is`(LoginActivity.LOGIN_SUCCESS_TAG))).check(matches(isDisplayed()))
    }
}