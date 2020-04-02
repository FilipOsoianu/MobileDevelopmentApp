package com.example.safenudesapp.Adapter

import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.safenudesapp.JsonAdapter.FriendRequest
import com.example.safenudesapp.JsonAdapter.User
import com.example.safenudesapp.R
import com.example.safenudesapp.repos.UsersRepository
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import kotlinx.android.synthetic.main.user_item.view.user_name
import kotlinx.android.synthetic.main.user_send_item.view.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class SendFriendshipAdapter(private val users: List<User>) :
    RecyclerView.Adapter<SendFriendshipAdapter.ViewHolder>() {
    private val usersRepository = UsersRepository()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.user_send_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val sharedPref: SharedPreferences = holder.context.getSharedPreferences("user", 0)
        val id = sharedPref.getInt("id", 0)
        holder.name.text = users[position].name
        holder.button.setOnClickListener {
            val updateRelationship = FriendRequest(id)
            val bodyJsonString = Gson().toJson(updateRelationship)
            val bodyJsonObject = JsonParser.parseString(bodyJsonString)
            println(bodyJsonString)
            GlobalScope.launch {
                kotlin.runCatching {

                    usersRepository.sendFriendRequest(
                        users[position].id,
                        bodyJsonObject as JsonObject
                    )
                }.onFailure {
                    println(it.printStackTrace())
                }
            }
        }
    }

    override fun getItemCount() = users.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.user_name
        val button: Button = itemView.button_send_friend_request
        val context = itemView.getContext();
    }
}