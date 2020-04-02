package com.example.safenudesapp.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.safenudesapp.Activity.ChatActivity
import com.example.safenudesapp.JsonAdapter.User
import com.example.safenudesapp.R
import kotlinx.android.synthetic.main.user_item.view.user_name
import kotlinx.android.synthetic.main.user_send_item.view.*


class SendFriendshipAdapter(private val users: List<User>) :
    RecyclerView.Adapter<SendFriendshipAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.user_send_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = users[position].name
        holder.button.setOnClickListener {

        }
    }

    override fun getItemCount() = users.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.user_name
        val button: Button = itemView.button_send_friend_request
        val context = itemView.getContext();
    }
}