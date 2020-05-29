
package com.example.safenudesapp


import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.runner.AndroidJUnit4
import com.example.safenudesapp.view.Activity.LoginActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.EnumSet.allOf


@RunWith(AndroidJUnit4::class)
class TestLogin {
    @get:Rule
    var intentsRule: IntentsTestRule<LoginActivity> = IntentsTestRule(LoginActivity::class.java)



    @Test
    fun verifyMessageSentToMessageActivity() {

        onView(withId(R.id.login_email))
            .perform(typeText("filip.osoianu@ati.utm.md"), closeSoftKeyboard())

        onView(withId(R.id.login_password))
            .perform(typeText("pass2"), closeSoftKeyboard())

        onView(withId(R.id.login_button)).perform(click())

    }

}

