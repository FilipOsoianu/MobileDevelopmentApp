package com.example.safenudesapp.repos

import com.example.safenudesapp.APIServices.ApiService
import com.example.safenudesapp.JsonAdapter.User
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UsersRepository {
    private fun retrofit(): ApiService {
      return  Retrofit.Builder()
            .baseUrl("http://192.168.1.117:8081")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(ApiService::class.java)
    }
    suspend fun fetchUsers(): List<User> {
        return  retrofit().fetchUsers()
    }

}