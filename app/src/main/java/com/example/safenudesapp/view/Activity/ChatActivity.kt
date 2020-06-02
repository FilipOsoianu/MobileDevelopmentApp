package com.example.safenudesapp.view.Activity

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.safenudesapp.view.Adapter.MessageAdapter
import com.example.safenudesapp.services.model.Chat
import com.example.safenudesapp.services.model.Message
import com.example.safenudesapp.services.model.SendMessage
import com.example.safenudesapp.R
import com.example.safenudesapp.services.utils.ChatParser
import com.example.safenudesapp.services.utils.UtilsJsonParse
import com.example.safenudesapp.viewModel.ChatViewModel
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
        val charParse = ChatParser()

        chatViewModel = ViewModelProvider(this).get(ChatViewModel::class.java)
        chatViewModel.listOfChats.observe(this, Observer {
            val chatList = it
            chatViewModel.fetchMessage(charParse.getChatId(chatList, userId, friendId))
            chatId.add(charParse.getChatId(chatList, userId, friendId))
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
                val utilsJsonParse = UtilsJsonParse()

                chatViewModel.sendMessage(
                    chatId.first(),
                    utilsJsonParse.convertSendMessageToJsonObject(message)
                )
                recycler_view_messages.smoothScrollToPosition(adapter.itemCount);
            }
        }
        recycler_view_messages.adapter = adapter
        recycler_view_messages.layoutManager = LinearLayoutManager(this@ChatActivity)
    }


}
