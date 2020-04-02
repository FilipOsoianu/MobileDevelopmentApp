package com.example.safenudesapp.Activity

import android.graphics.PorterDuff
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.safenudesapp.Adapter.ViewPagerAdapter
import com.example.safenudesapp.Fragments.ContactsFragment
import com.example.safenudesapp.Fragments.RequestsFragment
import com.example.safenudesapp.Fragments.SearchFriendsFragment
import com.example.safenudesapp.R
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import kotlinx.android.synthetic.main.activity_menu.*


@Suppress("DEPRECATION")
class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        val adapter = ViewPagerAdapter(supportFragmentManager)


        adapter.addFragment(ContactsFragment())
        adapter.addFragment(SearchFriendsFragment())
        adapter.addFragment(RequestsFragment())
        viewPager.adapter = adapter

        tabs.setupWithViewPager(viewPager)
        tabs.getTabAt(0)?.text = "Friends"
        tabs.getTabAt(1)?.text = "Search Friends"
        tabs.getTabAt(2)?.text = "Requests"


    }
}
