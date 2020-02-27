package com.example.safenudesapp.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.safenudesapp.Adapter.ContactAdapter
import com.example.safenudesapp.Items.ContactItem
import com.example.safenudesapp.R
import kotlinx.android.synthetic.main.activity_contacts.*

class ContactsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contacts)

        val contactList = generateDummyList(20);
        recycler_view.adapter =
            ContactAdapter(contactList)
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.setHasFixedSize(true)
    }

    private fun generateDummyList(size: Int): List<ContactItem> {

        val list = ArrayList<ContactItem>()
        val drawable = R.drawable.ic_person
        for (i in 0 until size) {
            val item =
                ContactItem(drawable, "Item $i")
            list += item
        }

        return list
    }
}
