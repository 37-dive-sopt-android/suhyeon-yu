package com.sopt.dive.screen.signin

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.dive.data.dto.request.RequestSignInDto
import com.sopt.dive.data.dto.response.ResponseLoginDto
import com.sopt.dive.data.dto.response.ServerResponse
import com.sopt.dive.data.repository.UserRepository
import kotlinx.coroutines.launch

class SignInViewModel : ViewModel() {
    private val repo = UserRepository()

    var loginState = mutableStateOf< ServerResponse<ResponseLoginDto>?>(null)
        private set

    fun login(id: String, pw: String) {
        viewModelScope.launch {
            loginState.value = repo.login(RequestSignInDto(id, pw))
        }
    }
}