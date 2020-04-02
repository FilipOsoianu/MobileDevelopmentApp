package com.example.safenudesapp.Activity

import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.safenudesapp.Adapter.MessageAdapter
import com.example.safenudesapp.JsonAdapter.Chat
import com.example.safenudesapp.JsonAdapter.Message
import com.example.safenudesapp.JsonAdapter.SendMessage
import com.example.safenudesapp.R
import com.example.safenudesapp.repos.UsersRepository
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import kotlinx.android.synthetic.main.activity_chat.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ChatActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        val sharedPref: SharedPreferences = getSharedPreferences("user", 0)
        val userEmail = sharedPref.getString("email", "")
        val userId = sharedPref.getInt("id", 0)
        val friendId = intent.getIntExtra("FRIEND_ID", 0)

        val usersRepository = UsersRepository()
        val messages = mutableListOf<Message>()
        val chatId = mutableListOf<Int>()
        val adapter = MessageAdapter(messages)

        fun fetch() {
            GlobalScope.launch {
                kotlin.runCatching {
                    val messageList = usersRepository.getMessages(chatId.first())
                    messages.clear()
                    for (message in messageList) {
                        messages.add(message)
                    }
                    runOnUiThread {
                        adapter.notifyDataSetChanged()
                    }
                    delay(1000)
                    fetch()
                }.onFailure {
                    println(it.printStackTrace())
                }
            }
        }

        GlobalScope.launch {
            val chatList = usersRepository.getChats(userEmail.toString())
            chatId.add(getChatId(chatList, userId, friendId))
            fetch()
        }

        button_send_message.setOnClickListener {
            GlobalScope.launch {
                kotlin.runCatching {
                    val message = SendMessage(friendId, userId, chat_input_text.text.toString())
                    val bodyJsonString = Gson().toJson(message)
                    val bodyJsonObject = JsonParser.parseString(bodyJsonString)
                    println(
                        usersRepository.sendMessage(chatId.first(), bodyJsonObject as JsonObject)
                    )
                }.onFailure {
                    println(it.printStackTrace())
                }.onSuccess {
                    recycler_view_messages.smoothScrollToPosition(adapter.itemCount);
                }
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
            }else{

            }
        }
        return 0
    }
}
