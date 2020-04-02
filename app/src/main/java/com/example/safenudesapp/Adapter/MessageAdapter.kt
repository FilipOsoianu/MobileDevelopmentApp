package com.example.safenudesapp.Adapter

import android.content.SharedPreferences
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.safenudesapp.JsonAdapter.Message
import com.example.safenudesapp.JsonAdapter.User
import com.example.safenudesapp.R
import kotlinx.android.synthetic.main.message_item.view.*

class MessageAdapter(private val messages: List<Message>) :
    RecyclerView.Adapter<MessageAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.message_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val sharedPref: SharedPreferences = holder.context.getSharedPreferences("user", 0)
        val id = sharedPref.getInt("id", 0)
        if (messages[position].fromUser == id) {
            holder.message.text = messages[position].message
            holder.messageBox.setBackgroundColor(ContextCompat.getColor(holder.context, R.color.accentColor))
//            holder.messageBox.setlayou
        }else{
            holder.message.text = messages[position].message
            holder.messageBox.setBackgroundColor(Color.parseColor("#FFFFFF"))
        }
    }

    override fun getItemCount() = messages.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val message: TextView = itemView.message
        val messageBox: RelativeLayout = itemView.message_layout
        val context = itemView.getContext();
    }
}