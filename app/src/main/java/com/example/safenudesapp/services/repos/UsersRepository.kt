package com.example.safenudesapp.services.repos

import com.example.safenudesapp.services.model.*
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UsersRepository {
    private fun retrofitUser(): ApiServiceUsers {
        return Retrofit.Builder()
            .baseUrl("http://192.168.1.117:8081")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(ApiServiceUsers::class.java)
    }


    suspend fun getUsers(): List<User> {
        return retrofitUser().fetchUsers()
    }


    suspend fun getFriends(id: Int): List<User> {
        return retrofitUser().fetchFriends(id)
    }

    suspend fun updateRelationship(userId: Int, friendId: Int, body: JsonObject): LoginResponse {
        return retrofitUser().updateRelationship(userId, friendId, body)
    }

    suspend fun sendFriendRequest(userId: Int, body: JsonObject): LoginResponse {
        return retrofitUser().sendFriendRequest(userId, body)
    }

    suspend fun getFriendRequests(id: Int): List<User> {
        return retrofitUser().fetchFriendRequests(id)
    }

    suspend fun getAccountInfo(email: String): AccountInfo {
        return retrofitUser().fetchAccountInfo(email)
    }

}