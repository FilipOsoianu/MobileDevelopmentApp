package com.example.safenudesapp.services.model
import com.google.gson.annotations.SerializedName

data class Account(
    @SerializedName("Email")
    val email: String? = null,
    @SerializedName("Password")
    val password: String? = null,
    @SerializedName("Name")
    val name: String? = null,
    @SerializedName("Avatar")
    val avatar: String? = null

)