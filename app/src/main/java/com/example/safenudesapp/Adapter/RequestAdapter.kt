package com.example.safenudesapp.Adapter

import android.content.Intent
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.safenudesapp.Activity.ChatActivity
import com.example.safenudesapp.JsonAdapter.Relationship
import com.example.safenudesapp.JsonAdapter.User
import com.example.safenudesapp.R
import com.example.safenudesapp.repos.UsersRepository
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import kotlinx.android.synthetic.main.user_item.view.*
import kotlinx.android.synthetic.main.user_item.view.user_name
import kotlinx.android.synthetic.main.user_request_item.view.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class RequestAdapter(private val users: List<User>) :
    RecyclerView.Adapter<RequestAdapter.ViewHolder>() {
    private val usersRepository = UsersRepository()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.user_request_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = users[position].name
        val sharedPref: SharedPreferences = holder.context.getSharedPreferences("user", 0)
        val id = sharedPref.getInt("id", 0)


        holder.buttonAccept.setOnClickListener {
            val updateRelationship = Relationship(2)
            val bodyJsonString = Gson().toJson(updateRelationship)
            val bodyJsonObject = JsonParser.parseString(bodyJsonString)
            GlobalScope.launch {
                usersRepository.updateRelationship(
                    id, users[position].id,
                    bodyJsonObject as JsonObject
                )
            }
        }

        holder.buttonDecline.setOnClickListener {
            val updateRelationship = Relationship(0)
            val bodyJsonString = Gson().toJson(updateRelationship)
            val bodyJsonObject = JsonParser.parseString(bodyJsonString)
            GlobalScope.launch {
                usersRepository.updateRelationship(
                    id, users[position].id,
                    bodyJsonObject as JsonObject
                )
            }
        }
    }

    override fun getItemCount() = users.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.user_name
        val buttonAccept: Button = itemView.button_accept_friendship
        val buttonDecline: Button = itemView.button_delete_friendship
        val context = itemView.getContext();
    }
}