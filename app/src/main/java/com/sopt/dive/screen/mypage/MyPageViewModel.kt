package com.sopt.dive.screen.mypage

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.dive.data.dto.response.ResponseUserInfoDto
import com.sopt.dive.data.repository.UserRepository
import kotlinx.coroutines.launch

class MyPageViewModel : ViewModel() {
    private val repo = UserRepository()

    var userInfoState = mutableStateOf<ResponseUserInfoDto?>(null)
        private set

    fun loadUserInfo(id: String) {
        viewModelScope.launch {
            userInfoState.value = repo.getUserInfo(id)
        }
    }
}