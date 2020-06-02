package com.example.safenudesapp.services.utils

class RegistrationCheck {
    fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun isPasswordValid(password: String, confirmPassword: String): Boolean {
        return password == confirmPassword
    }
}