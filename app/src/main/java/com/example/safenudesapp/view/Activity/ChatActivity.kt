package com.example.safenudesapp.view.Activity

import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.safenudesapp.view.Adapter.MessageAdapter
import com.example.safenudesapp.services.model.Chat
import com.example.safenudesapp.services.model.Message
import com.example.safenudesapp.services.model.SendMessage
import com.example.safenudesapp.R
import com.example.safenudesapp.viewModel.ChatViewModel
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import kotlinx.android.synthetic.main.activity_chat.*

class ChatActivity : AppCompatActivity() {
    private lateinit var chatViewModel: ChatViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        val sharedPref: SharedPreferences = getSharedPreferences("user", 0)
        val userEmail = sharedPref.getString("email", "")
        val userId = sharedPref.getInt("id", 0)
        val friendId = intent.getIntExtra("FRIEND_ID", 0)

        val messages = mutableListOf<Message>()
        val chatId = mutableListOf<Int>()
        val adapter = MessageAdapter(messages)


        chatViewModel = ViewModelProvider(this).get(ChatViewModel::class.java)
        chatViewModel.listOfChats.observe(this, Observer {
            val chatList = it
            chatViewModel.fetchMessage(getChatId(chatList, userId, friendId))
            chatId.add(getChatId(chatList, userId, friendId))
        })


        chatViewModel.listOfMessages.observe(this, Observer {
            messages.clear()
            messages.addAll(it)
            adapter.notifyDataSetChanged()
        })

        if (userEmail != null) {
            chatViewModel.fetchChatId(userEmail)
        }

        button_send_message.setOnClickListener {
            kotlin.runCatching {
                val message =
                    SendMessage(
                        friendId,
                        userId,
                        chat_input_text.text.toString()
                    )
                val bodyJsonString = Gson().toJson(message)
                val bodyJsonObject = JsonParser.parseString(bodyJsonString)
                chatViewModel.sendMessage(chatId.first(), bodyJsonObject as JsonObject)
                recycler_view_messages.smoothScrollToPosition(adapter.itemCount);
            }
        }
        recycler_view_messages.adapter = adapter
        recycler_view_messages.layoutManager = LinearLayoutManager(this@ChatActivity)
    }

    private fun getChatId(chatList: List<Chat>, userId: Int, friendId: Int): Int {
        for (chat in chatList) {
            if ((chat.userOne == userId && chat.userTwo == friendId) ||
                (chat.userTwo == userId && chat.userOne == friendId)
            ) {
                return chat.chatId
            }
        }
        return 0
    }
}
