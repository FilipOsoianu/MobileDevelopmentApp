package com.example.safenudesapp

import android.content.Context
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.runner.AndroidJUnit4
import com.example.safenudesapp.view.Activity.LoginActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.lang.String
import org.hamcrest.Matcher;
import androidx.test.espresso.assertion.ViewAssertions.matches
import com.example.safenudesapp.services.model.User
import com.example.safenudesapp.view.Adapter.ContactsAdapter

@RunWith(AndroidJUnit4::class)
class TestLoginAndSendMessage {
    @get:Rule
    var intentsRule: IntentsTestRule<LoginActivity> = IntentsTestRule(LoginActivity::class.java)

    @Test
    fun verifyLoginAndSendMessage() {

        onView(withId(R.id.login_email))
            .perform(typeText("filip.osoianu@ati.utm.md"), closeSoftKeyboard())

        onView(withId(R.id.login_password))
            .perform(typeText("pass2"), closeSoftKeyboard())

        onView(withId(R.id.login_button)).perform(click())

        onView(withId(R.id.recycler_view_contacts))
            .perform(RecyclerViewActions.actionOnItemAtPosition<ContactsAdapter.ViewHolder>(0, click()))

        onView(withId(R.id.chat_input_text))
            .perform(typeText("salut"), closeSoftKeyboard())

        onView(withId(R.id.button_send_message)).perform(click())
    }


    @Test
    fun verifyGoToCreateAccount() {
        onView(withId(R.id.create_account_button)).perform(click())
    }


}

