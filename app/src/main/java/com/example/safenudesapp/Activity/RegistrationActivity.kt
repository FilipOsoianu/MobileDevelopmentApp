package com.example.safenudesapp.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.example.safenudesapp.JsonAdapter.Account
import com.example.safenudesapp.R
import com.example.safenudesapp.repos.UsersRepository
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import kotlinx.android.synthetic.main.activity_login.registration_password
import kotlinx.android.synthetic.main.activity_registration.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RegistrationActivity : AppCompatActivity() {
    private val usersRepository = UsersRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)


        registration_button.setOnClickListener {


            GlobalScope.launch {
                kotlin.runCatching {
                    val email = registration_email.text.toString()
                    val password = registration_password.text.toString()
                    val confirmPassword = registration_confirm_password.text.toString()
                    val name = registration_name.text.toString()
                    val avatar = "Hi"
                    val account = Account(email, password, name, avatar)
                    val bodyJsonString = Gson().toJson(account)
                    val bodyJsonObject = JsonParser.parseString(bodyJsonString)
                    if (password != confirmPassword) {
                        runOnUiThread() {
                            val builder = AlertDialog.Builder(this@RegistrationActivity)
                            builder.setTitle("Error")
                            builder.setMessage("passwords not match")
                            builder.setIcon(android.R.drawable.ic_dialog_alert)
                            val alertDialog: AlertDialog = builder.create()
                            alertDialog.setCancelable(true)
                            alertDialog.show()
                        }
                    } else {
                        println(bodyJsonString)
                        println(usersRepository.registration(bodyJsonObject as JsonObject))
                    }
                }.onSuccess {
                    val contactsActivity =
                        Intent(this@RegistrationActivity, ContactsActivity::class.java)
                    startActivity(contactsActivity)
                }.onFailure {
                    runOnUiThread() {
                        val builder = AlertDialog.Builder(this@RegistrationActivity)
                        builder.setTitle("Error")
                        builder.setMessage("Some data are not valid")
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
