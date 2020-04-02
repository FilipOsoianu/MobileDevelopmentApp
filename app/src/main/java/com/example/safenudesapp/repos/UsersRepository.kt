package com.example.safenudesapp.repos

import com.example.safenudesapp.APIServices.ApiService
import com.example.safenudesapp.JsonAdapter.User
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UsersRepository {
    private fun retrofitUser(): ApiService {
        return Retrofit.Builder()
            .baseUrl("http://192.168.1.117:8081")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(ApiService::class.java)
    }

    private fun retrofitAuth(): ApiService {
        return Retrofit.Builder()
            .baseUrl("http://192.168.1.117:8082")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(ApiService::class.java)
    }

    suspend fun getUsers(): List<User> {
        return retrofitUser().fetchUsers()
    }

    suspend fun login(loginEncoded: String): String {
        return retrofitAuth().login(loginEncoded).message
    }

    suspend fun registration(body: JsonObject): String {
        return retrofitAuth().registration(body).message
    }

    suspend fun getFriends(): List<User> {
        return retrofitUser().fetchFriends()
    }

    suspend fun getFriendRequests(): List<User> {
        return retrofitUser().fetchFriendRequests()
    }
}