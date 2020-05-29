package com.example.safenudesapp.view.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.example.safenudesapp.services.model.Account
import com.example.safenudesapp.R
import androidx.lifecycle.Observer
import com.example.safenudesapp.viewModel.RegistrationViewModel
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import kotlinx.android.synthetic.main.activity_registration.*

class RegistrationActivity : AppCompatActivity() {
    private lateinit var registrationviewmodel: RegistrationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        registrationviewmodel = ViewModelProvider(this).get(RegistrationViewModel::class.java)
        registrationviewmodel.response.observe(this, Observer {
            val response = it
            if (response == "Please check and enter unique code ...") {
                val contactsActivity = Intent(this@RegistrationActivity, MenuActivity::class.java)
                startActivity(contactsActivity)
            } else {
                raiseError("Some data are not valid")
            }
        })

        registration_button.setOnClickListener {
            val email = registration_email.text.toString()
            val password = registration_password.text.toString()
            val confirmPassword = registration_confirm_password.text.toString()
            val name = registration_name.text.toString()
            val avatar = "Hi"
            if (isValidEmail(email)) {
                if (isPasswordValid(password, confirmPassword)) {
                    val account = Account(
                        email,
                        password,
                        name,
                        avatar
                    )
                    val bodyJsonString = Gson().toJson(account)
                    val bodyJsonObject = JsonParser.parseString(bodyJsonString)
                    registrationviewmodel.registration(bodyJsonObject as JsonObject);
                } else {
                    raiseError("passwords not match")
                }
            } else {
                raiseError("Not valid email")
            }
        }
    }

   fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun isPasswordValid(password: String, confirmPassword: String): Boolean {
        return password == confirmPassword
    }

    private fun raiseError(error: String) {
        val builder = AlertDialog.Builder(this@RegistrationActivity)
        builder.setTitle("Error")
        builder.setMessage(error)
        builder.setIcon(android.R.drawable.ic_dialog_alert)
        val alertDialog: AlertDialog = builder.create()
        alertDialog.setCancelable(true)
        alertDialog.show()
    }

}
