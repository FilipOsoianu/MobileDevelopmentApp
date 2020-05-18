package com.example.safenudesapp.view.Fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.safenudesapp.view.Adapter.ContactsAdapter
import com.example.safenudesapp.services.model.User
import com.example.safenudesapp.R
import com.example.safenudesapp.viewModel.ContactsViewModel
import kotlinx.android.synthetic.main.fragment_contacts.*


/**
 * A simple [Fragment] subclass.
 */
class ContactsFragment : Fragment() {
    private lateinit var contactsViewModel: ContactsViewModel
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contacts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val users = mutableListOf<User>()
        val adapter = ContactsAdapter(users)
        val sharedPref: SharedPreferences = activity!!.getSharedPreferences("user", 0)
        val id = sharedPref.getInt("id", 0)
        contactsViewModel = ViewModelProvider(this).get(ContactsViewModel::class.java)
        contactsViewModel.listOfFriends.observe(this, Observer {
            users.clear()
            users.addAll(it)
            adapter.notifyDataSetChanged()
        })

        contactsViewModel.fetch(id)

        recycler_view_contacts.adapter = adapter
        recycler_view_contacts.layoutManager = LinearLayoutManager(activity)
    }
}