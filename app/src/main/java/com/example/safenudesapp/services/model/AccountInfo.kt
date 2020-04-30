package com.example.safenudesapp.services.model


import com.google.gson.annotations.SerializedName

data class AccountInfo(
    @SerializedName("Id")
    val id: Int,
    @SerializedName("Email")
    val email: String,
    @SerializedName("Password")
    val password: String,
    @SerializedName("Avatar")
    val avatar: Any,
    @SerializedName("Name")
    val name: String
)