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

        for (i in 1..2) {
            users.add(User("Filip", 2, "dada", "dadada"))
        }

        GlobalScope.launch {
//            val list = usersRepository.fetchUsers()
//            for (user in list) {
//                users.add(user)
//            }
//            println(users)
        }



            recycler_view.apply {
                layoutManager = LinearLayoutManager(this@ContactsActivity)
                adapter = UsersAdapter(users)
            }
    }


}
