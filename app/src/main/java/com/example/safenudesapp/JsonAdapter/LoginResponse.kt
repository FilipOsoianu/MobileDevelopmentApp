package com.example.safenudesapp.JsonAdapter


import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("Message")
    val message: String
)