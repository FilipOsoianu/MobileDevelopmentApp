package com.example.safenudesapp.view.Adapter

import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.safenudesapp.services.model.CreateChat
import com.example.safenudesapp.services.model.Relationship
import com.example.safenudesapp.services.model.User
import com.example.safenudesapp.R
import com.example.safenudesapp.services.repos.ChatRepository
import com.example.safenudesapp.services.repos.UsersRepository
import com.example.safenudesapp.services.utils.UtilsJsonParse
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import kotlinx.android.synthetic.main.user_item.view.user_name
import kotlinx.android.synthetic.main.user_request_item.view.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class RequestAdapter(private val users: List<User>) :
    RecyclerView.Adapter<RequestAdapter.ViewHolder>() {
    private val chatRepository = ChatRepository()
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
        val email = sharedPref.getString("email", "")
        val utilsJsonParse = UtilsJsonParse()


        holder.buttonAccept.setOnClickListener {
            val updateRelationship = Relationship(2)
            val createChat = CreateChat(users[position].id)

            GlobalScope.launch {
                usersRepository.updateRelationship(
                    id, users[position].id,
                    utilsJsonParse.convertRelationshipToJsonObject(updateRelationship)
                )
                chatRepository.createChat(
                    email.toString(),
                    utilsJsonParse.convertCreateChatToJsonObject(createChat)
                )
            }
        }

        holder.buttonDecline.setOnClickListener {
            val updateRelationship =
                Relationship(0)

            GlobalScope.launch {
                usersRepository.updateRelationship(
                    id, users[position].id,
                    utilsJsonParse.convertRelationshipToJsonObject(updateRelationship)
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