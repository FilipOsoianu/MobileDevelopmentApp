package com.example.safenudesapp.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.safenudesapp.Items.ContactItem
import com.example.safenudesapp.R
import kotlinx.android.synthetic.main.contact_item.view.*

class ContactAdapter(private val contactList: List<ContactItem>) :RecyclerView.Adapter<ContactAdapter.ContactViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.contact_item, parent, false)

        return ContactViewHolder(
            itemView
        )
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val currentItem = contactList[position]
        holder.imageView.setImageResource(currentItem.imageResource)
        holder.textView.text = currentItem.text1
    }

    override fun getItemCount() = contactList.size

    class ContactViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val imageView: ImageView = itemView.contact_image
        val textView: TextView = itemView.contact_text
    }
}