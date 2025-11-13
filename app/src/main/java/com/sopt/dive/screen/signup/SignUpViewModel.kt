package com.sopt.dive.screen.signup

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.dive.data.dto.request.RequestSignUpDto
import com.sopt.dive.data.dto.response.ResponseSignUpDto
import com.sopt.dive.data.repository.UserRepository
import com.sopt.dive.data.dto.response.ServerResponse
import kotlinx.coroutines.launch

class SignUpViewModel : ViewModel() {

    private val repo = UserRepository()

    // 회원가입 성공 시: 데이터 존재
    var signUpState = mutableStateOf<ResponseSignUpDto?>(null)
        private set

    // 실패 시 UI에 띄워줄 에러 메시지
    var errorMessage = mutableStateOf<String?>(null)
        private set

    fun signUp(
        username: String,
        password: String,
        name: String,
        email: String,
        age: Int
    ) {
        viewModelScope.launch {
            try {
                val response: ServerResponse<ResponseSignUpDto> =
                    repo.signUp(RequestSignUpDto(username, password, name, email, age))

                if (response.success) {
                    // 회원가입 성공 → data 안에 유저 정보 들어있음
                    signUpState.value = response.data
                } else {
                    // 실패 → message 꺼내서 UI에서 토스트 띄우는 용도
                    errorMessage.value = response.message
                }

            } catch (e: Exception) {
                errorMessage.value = "서버 오류가 발생했습니다."
                e.printStackTrace()
            }
        }
    }
}