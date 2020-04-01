package com.example.safenudesapp.Activity

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.example.safenudesapp.R
import com.example.safenudesapp.repos.UsersRepository
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*

class LoginActivity : AppCompatActivity() {
    val usersRepository = UsersRepository()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        create_account_button.setOnClickListener {
            val registrationActivityIntent = Intent(this, RegistrationActivity::class.java)
            startActivity(registrationActivityIntent)
        }
        login_button.setOnClickListener {
            val encoded =
                registration_login.text.toString() + ":" + registration_password.text.toString()
            GlobalScope.launch {
                kotlin.runCatching {
                    usersRepository.login("ZmlsaXAub3NvaWFudUBhdGkudXRtLm1kOnBhc3My")
                    runOnUiThread {
                        val contactsActivity =
                            Intent(this@LoginActivity, ContactsActivity::class.java)
                        startActivity(contactsActivity)
                    }
                }.onSuccess {
                    println(it)
                }.onFailure {
                    println("jora")
                }
            }
        }

    }


}
