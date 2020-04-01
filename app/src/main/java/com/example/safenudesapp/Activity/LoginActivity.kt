package com.example.safenudesapp.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.safenudesapp.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        create_account_button.setOnClickListener {
            val registrationActivityIntent = Intent(this, RegistrationActivity::class.java)
            startActivity(registrationActivityIntent)
        }
    }


}
