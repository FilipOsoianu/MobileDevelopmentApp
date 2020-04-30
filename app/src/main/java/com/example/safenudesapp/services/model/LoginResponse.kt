package com.example.safenudesapp.services.model


import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("Message")
    val message: String
)