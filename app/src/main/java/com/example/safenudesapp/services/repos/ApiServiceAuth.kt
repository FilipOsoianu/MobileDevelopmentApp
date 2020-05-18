package com.example.safenudesapp.services.repos

import com.example.safenudesapp.services.model.*
import com.google.gson.JsonObject
import retrofit2.http.*

interface ApiServiceAuth {

    @GET("/api/login")
    suspend fun login(
        @Header("credentials") loginPasswordEncoder: String
    ): LoginResponse

    @POST("/api/registration")
    suspend fun registration(
        @Body body: JsonObject
    ): LoginResponse

}