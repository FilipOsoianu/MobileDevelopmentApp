package com.example.safenudesapp.view.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.safenudesapp.view.Adapter.SendFriendshipAdapter
import com.example.safenudesapp.services.model.User
import com.example.safenudesapp.R
import com.example.safenudesapp.viewModel.SearchFriendsViewModel
import kotlinx.android.synthetic.main.fragment_search_friends.*

/**
 * A simple [Fragment] subclass.
 */
class SearchFriendsFragment : Fragment() {
    private lateinit var searchFriendsViewModel: SearchFriendsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_friends, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val users = mutableListOf<User>()
        val adapter = SendFriendshipAdapter(users)

        searchFriendsViewModel = ViewModelProvider(this).get(SearchFriendsViewModel::class.java)
        searchFriendsViewModel.listOfUsers.observe(this, Observer {
            users.clear()
            users.addAll(it)
            adapter.notifyDataSetChanged()
        })
        searchFriendsViewModel.fetch()

        recycler_view_search_friends.adapter = adapter
        recycler_view_search_friends.layoutManager = LinearLayoutManager(activity)
    }
}
