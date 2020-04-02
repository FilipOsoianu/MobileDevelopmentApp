package com.example.safenudesapp.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.safenudesapp.Adapter.UsersAdapter
import com.example.safenudesapp.JsonAdapter.User
import com.example.safenudesapp.R
import com.example.safenudesapp.repos.UsersRepository
import kotlinx.android.synthetic.main.activity_users.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ContactsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users)
        val usersRepository = UsersRepository()
        val users = mutableListOf<User>()
        val adapter = UsersAdapter(users)

        GlobalScope.launch {
            val list = usersRepository.getUsers()
            for (user in list) {
                users.add(user)
            }
            runOnUiThread {
                adapter.notifyDataSetChanged()
            }
        }
        recycler_view.adapter = adapter
        recycler_view.layoutManager = LinearLayoutManager(this@ContactsActivity)
    }
}
