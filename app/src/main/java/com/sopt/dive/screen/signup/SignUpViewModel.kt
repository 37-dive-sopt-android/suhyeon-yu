package com.sopt.dive.screen.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.dive.data.dto.request.RequestSignUpDto
import com.sopt.dive.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed interface SignUpEvent {
    data class Success(val message: String) : SignUpEvent
    data class Error(val message: String) : SignUpEvent
}

class SignUpViewModel : ViewModel() {

    private val repo = UserRepository()

    private val _uiState = MutableStateFlow(SignUpUiState())
    val uiState = _uiState.asStateFlow()

    private val _event = MutableSharedFlow<SignUpEvent>()
    val event = _event.asSharedFlow()

    fun updateUsername(value: String) {
        _uiState.value = _uiState.value.copy(username = value)
    }

    fun updatePassword(value: String) {
        _uiState.value = _uiState.value.copy(password = value)
    }

    fun updateName(value: String) {
        _uiState.value = _uiState.value.copy(name = value)
    }

    fun updateEmail(value: String) {
        _uiState.value = _uiState.value.copy(email = value)
    }

    fun updateAge(value: String) {
        _uiState.value = _uiState.value.copy(age = value)
    }

    fun signUp() {
        val state = _uiState.value
        val ageInt = state.age.toIntOrNull()

        if (state.username.isBlank() ||
            state.password.isBlank() ||
            state.name.isBlank() ||
            state.email.isBlank() ||
            ageInt == null
        ) {
            viewModelScope.launch {
                _event.emit(SignUpEvent.Error("모든 항목을 올바르게 입력해주세요."))
            }
            return
        }

        viewModelScope.launch {
            _uiState.value = state.copy(isLoading = true)

            try {
                val response = repo.signUp(
                    RequestSignUpDto(
                        username = state.username,
                        password = state.password,
                        name = state.name,
                        email = state.email,
                        age = ageInt
                    )
                )

                if (response.success) {
                    _event.emit(SignUpEvent.Success("회원가입에 성공했습니다."))
                } else {
                    _event.emit(SignUpEvent.Error(response.message))
                }

            } catch (e: Exception) {
                _event.emit(SignUpEvent.Error("서버 오류가 발생했습니다."))
            } finally {
                _uiState.value = _uiState.value.copy(isLoading = false)
            }
        }
    }
}