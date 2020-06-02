package com.example.safenudesapp

import android.util.Log
import org.junit.Test
import com.example.safenudesapp.services.model.FriendRequest
import com.example.safenudesapp.services.utils.RegistrationCheck
import com.example.safenudesapp.services.utils.UtilsJsonParse
import io.mockk.every
import io.mockk.mockk
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat

class RegistrationUnitTest {
    private val registrationCheck = RegistrationCheck()

    @Test
    fun checkPassword() {
        val response = registrationCheck.isPasswordValid("123", "123")
        assertThat(response, `is`(true))
    }
}