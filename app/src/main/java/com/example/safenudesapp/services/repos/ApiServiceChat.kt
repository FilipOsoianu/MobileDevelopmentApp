package com.example.safenudesapp.services.repos

import com.example.safenudesapp.services.model.*
import com.google.gson.JsonObject
import retrofit2.http.*


interface ApiServiceChat {
    @GET("/api/chats/{id}/messages")
    suspend fun fetchMessage(
        @Path("id") id: Int
    ): List<Message>

    @GET("/api/chats")
    suspend fun fetchChats(
        @Query("email") email: String
    ): List<Chat>

    @POST("/api/chats/{chatId}/messages")
    suspend fun sendMessage(
        @Path("chatId") chatId: Int,
        @Body body: JsonObject
    ): LoginResponse

    @POST("/api/chats")
    suspend fun createChat(
        @Query("email") email: String,
        @Body body: JsonObject
    ): LoginResponse
}