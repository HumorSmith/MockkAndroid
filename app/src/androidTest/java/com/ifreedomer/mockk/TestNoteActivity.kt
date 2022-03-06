package com.ifreedomer.mockk

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ifreedomer.mockk.login.LoginActivity
import com.ifreedomer.mockk.note.NoteActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TestNoteActivity {
    @get:Rule
    var testRule = ActivityScenarioRule(NoteActivity::class.java)

    @Test
    fun testTitleSort() {
        //withId withText  withResourceName  withTagKey  withTagValue
        Espresso.onView(ViewMatchers.withId(R.id.title_btn))
            .perform(ViewActions.click())
    }
}