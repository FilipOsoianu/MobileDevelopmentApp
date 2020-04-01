package com.example.safenudesapp.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.safenudesapp.Adapter.ContactAdapter
import com.example.safenudesapp.Items.ContactItem
import com.example.safenudesapp.JsonAdapter.User
import com.example.safenudesapp.R
import com.example.safenudesapp.repos.UsersRepository
import kotlinx.android.synthetic.main.activity_contacts.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.converter.gson.GsonConverterFactory

class ContactsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contacts)
        val usersRepository = UsersRepository()


        GlobalScope.launch {
            val list = generateListOfContacts(usersRepository.fetchUsers());
        }
            recycler_view.adapter = ContactAdapter(list)
            recycler_view.layoutManager = LinearLayoutManager(this)
            recycler_view.setHasFixedSize(true)


    }


    private fun generateListOfContacts(list: List<User>): ArrayList<ContactItem> {
        val listOfContactItem = ArrayList<ContactItem>()
        val drawable = R.drawable.ic_person
        for (user in list) {
            val item =
                ContactItem(drawable, user.name)
            listOfContactItem += item
        }
        return listOfContactItem
    }
}
