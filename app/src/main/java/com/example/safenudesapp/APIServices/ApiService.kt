package com.example.safenudesapp.APIServices

import com.example.safenudesapp.JsonAdapter.LoginResponse
import com.example.safenudesapp.JsonAdapter.User
import com.google.gson.Gson
import com.google.gson.JsonObject
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST


interface ApiService {
    @GET("/api/users")
    suspend fun fetchUsers(): List<User>

    @GET("/api/login")
    suspend fun login(
        @Header("credentials") loginPasswordEncoder: String
    ): LoginResponse

    @POST("/api/registration")
    suspend fun registration(
       @Body body: JsonObject
    ): LoginResponse

}