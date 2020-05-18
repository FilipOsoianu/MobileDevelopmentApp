package com.example.safenudesapp.services.repos

import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AuthRepository {


    private fun retrofitAuth(): ApiServiceAuth {
        return Retrofit.Builder()
            .baseUrl("http://192.168.1.117:8082")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(ApiServiceAuth::class.java)
    }

    suspend fun login(loginEncoded: String): String {
        return retrofitAuth().login(loginEncoded).message
    }

    suspend fun registration(body: JsonObject): String {
        return retrofitAuth().registration(body).message
    }

}