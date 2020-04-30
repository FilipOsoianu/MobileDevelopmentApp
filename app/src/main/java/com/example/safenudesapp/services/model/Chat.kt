package com.example.safenudesapp.services.model


import com.google.gson.annotations.SerializedName

data class Chat(
    @SerializedName("user_one")
    val userOne: Int,
    @SerializedName("user_two")
    val userTwo: Int,
    @SerializedName("chat_id")
    val chatId: Int
)