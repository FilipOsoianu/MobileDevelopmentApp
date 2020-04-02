package com.example.safenudesapp.Fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.safenudesapp.Adapter.ContactsAdapter
import com.example.safenudesapp.JsonAdapter.User
import com.example.safenudesapp.R
import com.example.safenudesapp.repos.UsersRepository
import kotlinx.android.synthetic.main.fragment_contacts.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


/**
 * A simple [Fragment] subclass.
 */
class ContactsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contacts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val usersRepository = UsersRepository()
        val users = mutableListOf<User>()
        val adapter = ContactsAdapter(users)
        val sharedPref: SharedPreferences = activity!!.getSharedPreferences("user", 0)
        val id = sharedPref.getInt("id", 0)
        fun fetch() {
            GlobalScope.launch {
                users.clear()
                val list = usersRepository.getFriends(id)
                for (user in list) {
                    users.add(user)
                }
                activity?.runOnUiThread {
                    adapter.notifyDataSetChanged()
                }
            delay(1000)
            fetch()
            }
        }
        fetch()
        recycler_view_contacts.adapter = adapter
        recycler_view_contacts.layoutManager = LinearLayoutManager(activity)
    }

}
