package com.example.safenudesapp.view.Fragments

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.safenudesapp.view.Adapter.RequestAdapter
import com.example.safenudesapp.services.model.User
import com.example.safenudesapp.R
import com.example.safenudesapp.services.repos.UsersRepository
import com.example.safenudesapp.viewModel.RequestFriendsViewModel
import kotlinx.android.synthetic.main.fragment_requests.*
import kotlinx.coroutines.delay

/**
 * A simple [Fragment] subclass.
 */
class RequestsFragment : Fragment() {
    private lateinit var requestViewModel: RequestFriendsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_requests, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val users = mutableListOf<User>()
        val adapter = RequestAdapter(users)
        val sharedPref: SharedPreferences = activity!!.getSharedPreferences("user", 0)
        val id = sharedPref.getInt("id", 0)

        requestViewModel = ViewModelProvider(this).get(RequestFriendsViewModel::class.java)
        requestViewModel.listOfRequests.observe(this, Observer {
            users.clear()
            users.addAll(it)
            adapter.notifyDataSetChanged()
        })
        requestViewModel.fetch(id)

        recycler_view_requests.adapter = adapter
        recycler_view_requests.layoutManager = LinearLayoutManager(activity)
    }
}
