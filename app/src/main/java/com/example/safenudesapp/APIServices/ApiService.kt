package com.example.safenudesapp.APIServices

import com.example.safenudesapp.JsonAdapter.User
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("/api/users")
    suspend fun fetchUsers(): List<User>
}