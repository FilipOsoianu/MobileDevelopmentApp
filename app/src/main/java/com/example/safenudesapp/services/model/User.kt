package com.example.safenudesapp.services.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("Name")
    val name: String,
    @SerializedName("Id")
    val id: Int,
    @SerializedName("Avatar")
    val avatar: String,
    @SerializedName("Email")
    val email: String
)