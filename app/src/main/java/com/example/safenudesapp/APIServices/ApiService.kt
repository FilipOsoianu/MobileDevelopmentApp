package com.example.safenudesapp.APIServices

import com.example.safenudesapp.JsonAdapter.LoginResponse
import com.example.safenudesapp.JsonAdapter.User
import retrofit2.http.GET
import retrofit2.http.Header


interface ApiService {
    @GET("/api/users")
    suspend fun fetchUsers(): List<User>

    @GET("/api/login")
    suspend fun login(
        @Header("credentials") loginPasswordEncoder: String
    ): LoginResponse
}