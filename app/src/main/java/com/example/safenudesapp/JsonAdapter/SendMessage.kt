package com.example.safenudesapp.JsonAdapter


import com.google.gson.annotations.SerializedName

data class SendMessage(
    @SerializedName("to_user")
    val toUser: Int,
    @SerializedName("from_user")
    val fromUser: Int,
    val message: String
)