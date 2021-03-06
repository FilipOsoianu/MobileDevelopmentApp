package com.example.safenudesapp.view.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.example.safenudesapp.services.model.Account
import com.example.safenudesapp.R
import androidx.lifecycle.Observer
import com.example.safenudesapp.services.utils.RegistrationCheck
import com.example.safenudesapp.services.utils.UtilsJsonParse
import com.example.safenudesapp.viewModel.RegistrationViewModel
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
            val utilsJsonParse = UtilsJsonParse()
            val registrationCheck = RegistrationCheck()
            if (registrationCheck.isValidEmail(email)) {
                if (registrationCheck.isPasswordValid(password, confirmPassword)) {
                    val account = Account(
                        email,
                        password,
                        name,
                        avatar
                    )
                    registrationviewmodel.registration(utilsJsonParse.convertAccountToJsonObject(account));
                } else {
                    raiseError("passwords not match")
                }
            } else {
                raiseError("Not valid email")
            }
        }
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
