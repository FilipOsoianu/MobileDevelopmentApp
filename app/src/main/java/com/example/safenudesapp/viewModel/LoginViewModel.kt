package com.example.safenudesapp.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.safenudesapp.services.model.AccountInfo
import com.example.safenudesapp.services.repos.AuthRepository
import com.example.safenudesapp.services.repos.UsersRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val authRepository = AuthRepository()
    private val usersRepository = UsersRepository()
    val userDetails = MutableLiveData<AccountInfo>()
    val response = MutableLiveData<String>()

    fun login(encoded: String, email: String) {
        viewModelScope.launch(Dispatchers.Default) {
            kotlin.runCatching {
                authRepository.login(encoded)
            }.onSuccess {
                val user = usersRepository.getAccountInfo(email)
                userDetails.postValue(user)
                response.postValue(it)
            }.onFailure {
                it.printStackTrace()
                response.postValue("fail")
            }
        }
    }

}