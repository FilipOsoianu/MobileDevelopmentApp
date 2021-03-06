package com.example.safenudesapp.view.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.safenudesapp.view.Activity.ChatActivity
import com.example.safenudesapp.services.model.User
import com.example.safenudesapp.R
import kotlinx.android.synthetic.main.user_item.view.*


class ContactsAdapter(private val users: List<User>) :
    RecyclerView.Adapter<ContactsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = users[position].name
        holder.button.setOnClickListener {
            val intent = Intent(holder.context, ChatActivity::class.java)
            intent.putExtra("FRIEND_ID", users[position].id);
            holder.context.startActivity(intent)
        }
    }

    override fun getItemCount() = users.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.user_name
        val button: RelativeLayout = itemView.button_open_friend
        val context = itemView.getContext();
    }
}