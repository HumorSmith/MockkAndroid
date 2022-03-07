package com.ifreedomer.mockk

import android.content.ClipData
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ifreedomer.mockk.login.LoginActivity
import com.ifreedomer.mockk.note.NoteActivity
import com.ifreedomer.mockk.note.entity.NoteEntity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.espresso.IdlingRegistry
import com.ifreedomer.mockk.util.SimpleIdlingResource
import org.junit.Before


@RunWith(AndroidJUnit4::class)
class TestNoteActivity {
    @get:Rule
    var testRule = ActivityScenarioRule(NoteActivity::class.java)
    lateinit var data: MutableList<NoteEntity>;
    var idleResource: SimpleIdlingResource? = null
    private lateinit var activity: NoteActivity

    @Before
    fun setup() {
        testRule.scenario.onActivity {
            activity = it as NoteActivity
            idleResource = activity.idleResource
            // To prove that the test fails, omit this call:
            IdlingRegistry.getInstance().register(activity.idleResource)
        }
    }


    @Test
    fun testTitleSortAsc() {
        //withId withText  withResourceName  withTagKey  withTagValue
        onView(withId(R.id.title_btn))
            .perform(ViewActions.click())
        onView(withId(R.id.asc_btn))
            .perform(ViewActions.click())
        data = activity.data
        for (i in 1 until data.size) {
            if (data[i].title.toInt() - data[i-1].title.toInt()<0) {
                assert(false);
            }
        }
    }

    @Test
    fun testTitleSortDesc() {
        //withId withText  withResourceName  withTagKey  withTagValue
        onView(withId(R.id.title_btn))
            .perform(ViewActions.click())
        onView(withId(R.id.desc_btn))
            .perform(ViewActions.click())
        data = activity.data
        for (i in 1 until data.size) {
            if (data[i].title.toInt() - data[i-1].title.toInt()>0) {
                assert(false);
            }
        }
    }
}