package com.example.safenudesapp.Activity

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.safenudesapp.Adapter.ViewPagerAdapter
import com.example.safenudesapp.Fragments.ContactsFragment
import com.example.safenudesapp.Fragments.RequestsFragment
import com.example.safenudesapp.Fragments.SearchFriendsFragment
import com.example.safenudesapp.R
import kotlinx.android.synthetic.main.activity_menu.*

class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        val adapter = ViewPagerAdapter(supportFragmentManager)

        adapter.addFragment(ContactsFragment())
        adapter.addFragment(RequestsFragment())
        adapter.addFragment(SearchFriendsFragment())

        viewPager.adapter = adapter
        tabs.setupWithViewPager(viewPager)
        val sharedPref: SharedPreferences = getSharedPreferences("user", 0)

        val email = sharedPref.getString("email", "")
        val id = sharedPref.getInt("id", 0)
    }
}
