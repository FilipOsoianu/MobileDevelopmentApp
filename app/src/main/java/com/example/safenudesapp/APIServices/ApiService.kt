package com.example.safenudesapp.APIServices

import com.example.safenudesapp.JsonAdapter.*
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

    @GET("/api/chats/{id}/messages")
    suspend fun fetchMessage(
        @Path("id") id: Int
    ): List<Message>


    @GET("/api/users/{id}/requests")
    suspend fun fetchFriendRequests(
        @Path("id") id: Int
    ): List<User>

    @GET("/api/users")
    suspend fun fetchAccountInfo(
        @Query("email") email: String
    ): AccountInfo

    @GET("/api/chats")
    suspend fun fetchChats(
        @Query("email") email: String
    ): List<Chat>

    @POST("/api/registration")
    suspend fun registration(
        @Body body: JsonObject
    ): LoginResponse

    @POST("/api/chats/{chatId}/messages")
    suspend fun sendMessage(
        @Path("chatId") chatId: Int,
        @Body body: JsonObject
    ): LoginResponse

    @PUT("/api/users/{userId}/friends/{friendId}")
    suspend fun updateRelationship(
        @Path("userId") userId: Int,
        @Path("friendId") friendId: Int,
        @Body body: JsonObject
    ): LoginResponse

}