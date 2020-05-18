package com.example.safenudesapp.services.repos

import com.example.safenudesapp.services.model.*
import com.google.gson.JsonObject
import retrofit2.http.*


interface ApiServiceUsers {
    @GET("/api/users")
    suspend fun fetchUsers(): List<User>


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


    @PUT("/api/users/{userId}/friends/{friendId}")
    suspend fun updateRelationship(
        @Path("userId") userId: Int,
        @Path("friendId") friendId: Int,
        @Body body: JsonObject
    ): LoginResponse

    @POST("/api/users/{userId}/friends")
    suspend fun sendFriendRequest(
        @Path("userId") userId: Int,
        @Body body: JsonObject
    ): LoginResponse
}