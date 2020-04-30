package com.example.safenudesapp.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.safenudesapp.services.model.Chat
import com.example.safenudesapp.services.model.Message
import com.example.safenudesapp.services.repos.UsersRepository
import com.google.gson.JsonObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ChatViewModel : ViewModel() {
    private val usersRepository = UsersRepository()
    val listOfMessages = MutableLiveData<List<Message>>()
    val listOfChats = MutableLiveData<List<Chat>>()

    fun fetchMessage(id: Int) {
        viewModelScope.launch(Dispatchers.Default) {
            kotlin.runCatching {
                usersRepository.getMessages(id)
            }.onSuccess {
                listOfMessages.postValue(it)
                delay(1000)
                fetchMessage(id)
            }.onFailure {
                it.printStackTrace()
            }
        }
    }

    fun fetchChatId(email: String){
        viewModelScope.launch(Dispatchers.Default) {
            kotlin.runCatching {
                usersRepository.getChats(email)
            }.onSuccess {
                listOfChats.postValue(it)
            }.onFailure {
                it.printStackTrace()
            }
        }
    }


    fun sendMessage(chatId: Int, body: JsonObject){
        viewModelScope.launch(Dispatchers.Default) {
            kotlin.runCatching {
                usersRepository.sendMessage(chatId, body)
            }.onSuccess {
            }.onFailure {
                it.printStackTrace()
            }
        }
    }

}