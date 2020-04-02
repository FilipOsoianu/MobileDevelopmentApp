package com.example.safenudesapp.Fragments

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.safenudesapp.Adapter.RequestAdapter
import com.example.safenudesapp.JsonAdapter.User
import com.example.safenudesapp.R
import com.example.safenudesapp.repos.UsersRepository
import kotlinx.android.synthetic.main.fragment_requests.*
import kotlinx.android.synthetic.main.fragment_search_friends.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass.
 */
class RequestsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_requests, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val usersRepository = UsersRepository()
        val users = mutableListOf<User>()
        val adapter = RequestAdapter(users)
        val sharedPref: SharedPreferences = activity!!.getSharedPreferences("user", 0)
        val id = sharedPref.getInt("id", 0)
        fun fetch() {
            GlobalScope.launch {
                users.clear()
                val list = usersRepository.getFriendRequests(id)
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
        recycler_view_requests.adapter = adapter
        recycler_view_requests.layoutManager = LinearLayoutManager(activity)
    }
}
