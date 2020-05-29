package com.example.safenudesapp.view.Activity

import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.safenudesapp.R
import com.example.safenudesapp.viewModel.LoginViewModel
import kotlinx.android.synthetic.main.activity_login.*
import java.util.*

class LoginActivity : AppCompatActivity() {
    //    private val usersRepository = UsersRepository()
    private lateinit var loginviewmodel: LoginViewModel

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        create_account_button.setOnClickListener {
            val registrationActivityIntent = Intent(this, RegistrationActivity::class.java)
            startActivity(registrationActivityIntent)
        }

        loginviewmodel = ViewModelProvider(this).get(LoginViewModel::class.java)
        loginviewmodel.userDetails.observe(this, Observer {
            val user = it
            val id = user.id
            val sharedPref: SharedPreferences = getSharedPreferences("user", 0)
            val editor = sharedPref.edit()
            editor.putString("email", user.email)
            editor.putInt("id", id)
            editor.apply()

        })

        loginviewmodel.response.observe(this, Observer {
            val response = it
            if (response != "fail") {
                val intent = Intent(this, MenuActivity::class.java)
                startActivity(intent)
            } else {
                raiseError("Wrong Login or password")
            }
        })

        login_button.setOnClickListener {
            val login = login_email.text.toString()
            val password = login_password.text.toString()
            val loginPass = "$login:$password"
            val encoded = Base64.getEncoder().encodeToString(loginPass.toByteArray())
            loginviewmodel.login(encoded, login)
        }
    }


    private fun raiseError(error: String) {
        val builder = AlertDialog.Builder(this@LoginActivity)
        builder.setTitle("Error")
        builder.setMessage(error)
        builder.setIcon(android.R.drawable.ic_dialog_alert)
        val alertDialog: AlertDialog = builder.create()
        alertDialog.setCancelable(true)
        alertDialog.show()
    }
}
