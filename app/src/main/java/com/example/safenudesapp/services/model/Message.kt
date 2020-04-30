package com.example.safenudesapp.services.model


import com.google.gson.annotations.SerializedName

data class Message(
    @SerializedName("to_user")
    val toUser: Int,
    val chat: Int,
    @SerializedName("Id")
    val id: Int,
    val message: String,
    @SerializedName("inclusion_date")
    val inclusionDate: String,
    @SerializedName("from_user")
    val fromUser: Int,
    val status: String
)