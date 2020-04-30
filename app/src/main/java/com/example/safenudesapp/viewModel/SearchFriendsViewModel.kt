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

class SearchFriendsViewModel : ViewModel() {
    private val usersRepository = UsersRepository()
    val listOfUsers = MutableLiveData<List<User>>()

    fun fetch() {
        viewModelScope.launch(Dispatchers.Default) {
            kotlin.runCatching {
                usersRepository.getUsers()
            }.onSuccess {
                listOfUsers.postValue(it)
                delay(1000)
                fetch()
            }.onFailure {
                it.printStackTrace()
            }
        }
    }
}