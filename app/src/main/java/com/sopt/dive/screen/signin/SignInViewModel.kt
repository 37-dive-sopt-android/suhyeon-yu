package com.sopt.dive.screen.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.dive.data.dto.request.RequestSignInDto
import com.sopt.dive.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class SignInViewModel : ViewModel() {

    private val repo = UserRepository()

    private val _loginEvent = MutableSharedFlow<LoginEvent>()
    val loginEvent = _loginEvent.asSharedFlow()

    fun login(id: String, pw: String) {
        viewModelScope.launch {
            try {
                val response = repo.login(RequestSignInDto(id, pw))

                if (response.success && response.data != null) {
                    _loginEvent.emit(LoginEvent.Success(response.data.userId.toString()))
                } else {
                    _loginEvent.emit(LoginEvent.Failure(response.message ?: "로그인에 실패했습니다."))
                }

            } catch (e: Exception) {
                _loginEvent.emit(LoginEvent.Failure("로그인에 실패했습니다."))
            }
        }
    }
}

sealed class LoginEvent {
    data class Success(val userId: String) : LoginEvent()
    data class Failure(val message: String) : LoginEvent()
}