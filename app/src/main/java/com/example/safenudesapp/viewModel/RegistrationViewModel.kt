package com.example.safenudesapp.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.safenudesapp.services.repos.AuthRepository
import com.example.safenudesapp.services.repos.UsersRepository
import com.google.gson.JsonObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegistrationViewModel : ViewModel() {
    private val authRepository = AuthRepository()
    val response = MutableLiveData<String>()

    fun registration(body: JsonObject) {
        viewModelScope.launch(Dispatchers.Default) {
            kotlin.runCatching {
                authRepository.registration(body)
            }.onSuccess {
                response.postValue(it)
            }.onFailure {
                it.printStackTrace()
            }
        }
    }

}