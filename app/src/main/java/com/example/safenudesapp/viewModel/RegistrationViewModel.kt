package com.example.safenudesapp.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.safenudesapp.services.repos.UsersRepository
import com.google.gson.JsonObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegistrationViewModel : ViewModel() {
    private val usersRepository = UsersRepository()
    val response = MutableLiveData<String>()

    fun registration(body: JsonObject) {
        println(body)
        viewModelScope.launch(Dispatchers.Default) {
            kotlin.runCatching {
                usersRepository.registration(body)
            }.onSuccess {
                response.postValue(it)
            }.onFailure {
                it.printStackTrace()
            }
        }
    }

}