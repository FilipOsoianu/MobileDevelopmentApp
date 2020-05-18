package com.example.safenudesapp.services.repos

import com.example.safenudesapp.services.model.*
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ChatRepository {
    private fun retrofitChat(): ApiServiceChat {
        return Retrofit.Builder()
            .baseUrl("http://192.168.1.117:8089")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(ApiServiceChat::class.java)
    }

    suspend fun createChat(email: String, body: JsonObject): LoginResponse {
        return retrofitChat().createChat(email, body)
    }

    suspend fun getMessages(id: Int): List<Message> {
        return retrofitChat().fetchMessage(id)
    }

    suspend fun getChats(email: String): List<Chat> {
        return retrofitChat().fetchChats(email)
    }

    suspend fun sendMessage(chatId: Int, body: JsonObject): LoginResponse {
        return retrofitChat().sendMessage(chatId, body)
    }

}