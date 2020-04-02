package com.example.safenudesapp.APIServices

import com.example.safenudesapp.JsonAdapter.Account
import com.example.safenudesapp.JsonAdapter.AccountInfo
import com.example.safenudesapp.JsonAdapter.LoginResponse
import com.example.safenudesapp.JsonAdapter.User
import com.google.gson.Gson
import com.google.gson.JsonObject
import retrofit2.http.*


interface ApiService {
    @GET("/api/users")
    suspend fun fetchUsers(): List<User>

    @GET("/api/login")
    suspend fun login(
        @Header("credentials") loginPasswordEncoder: String
    ): LoginResponse

    @GET("/api/users/{id}/friends")
    suspend fun fetchFriends(
        @Path("id") id: Int
    ): List<User>

    @GET("/api/users/{id}/requests")
    suspend fun fetchFriendRequests(
        @Path("id") id: Int
    ): List<User>

    @GET("/api/users")
    suspend fun fetchAccountInfo(
        @Query("email") email: String
    ): AccountInfo


    @POST("/api/registration")
    suspend fun registration(
        @Body body: JsonObject
    ): LoginResponse

}