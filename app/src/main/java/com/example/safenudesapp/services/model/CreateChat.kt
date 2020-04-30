package com.example.safenudesapp.services.model


import com.google.gson.annotations.SerializedName

data class CreateChat(
    @SerializedName("user_two")
    val userTwo: Int
)