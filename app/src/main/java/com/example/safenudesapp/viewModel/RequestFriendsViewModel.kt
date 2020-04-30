package com.example.safenudesapp.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.safenudesapp.services.model.AccountInfo
import com.example.safenudesapp.services.model.User
import com.example.safenudesapp.services.repos.UsersRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class RequestFriendsViewModel : ViewModel() {
    private val usersRepository = UsersRepository()
    val listOfRequests = MutableLiveData<List<User>>()

    fun fetch(id: Int) {
        viewModelScope.launch(Dispatchers.Default) {
            kotlin.runCatching {
                usersRepository.getFriendRequests(id)
            }.onSuccess {
                listOfRequests.postValue(it)
                delay(1000)
                fetch(id)
            }.onFailure {
                it.printStackTrace()
            }
        }
    }
}