package com.example.safenudesapp.Activity

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import com.example.safenudesapp.R
import com.example.safenudesapp.repos.UsersRepository
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*

class LoginActivity : AppCompatActivity() {
    private val usersRepository = UsersRepository()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        create_account_button.setOnClickListener {
            val registrationActivityIntent = Intent(this, RegistrationActivity::class.java)
            startActivity(registrationActivityIntent)
        }
        login_button.setOnClickListener {
            val loginPass =
                registration_email.text.toString() + ":" + registration_password.text.toString()
            val encoded = Base64.getEncoder().encodeToString(loginPass.toByteArray())
            GlobalScope.launch {
                kotlin.runCatching {
                    usersRepository.login(encoded)

                }.onSuccess {
                    val contactsActivity =
                        Intent(this@LoginActivity, ContactsActivity::class.java)
                    startActivity(contactsActivity)
                }.onFailure {
                    runOnUiThread() {
                        val builder = AlertDialog.Builder(this@LoginActivity)
                        builder.setTitle("Error")
                        builder.setMessage("Wrong Login or password")
                        builder.setIcon(android.R.drawable.ic_dialog_alert)
                        val alertDialog: AlertDialog = builder.create()
                        alertDialog.setCancelable(true)
                        alertDialog.show()
                    }
                }
            }
        }
    }


}
