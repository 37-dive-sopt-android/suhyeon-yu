package com.sopt.dive.data.repository

import com.sopt.dive.data.dto.request.RequestSignInDto
import com.sopt.dive.data.dto.request.RequestSignUpDto
import com.sopt.dive.data.dto.response.ResponseSignUpDto
import com.sopt.dive.data.dto.response.ServerResponse
import com.sopt.dive.data.service.UserService
import com.sopt.dive.network.ServicePool

class UserRepository(
    private val service: UserService = ServicePool.userService
) {
    suspend fun login(request: RequestSignInDto) = service.login(request)
    suspend fun signUp(request: RequestSignUpDto): ServerResponse<ResponseSignUpDto> {
        return ServicePool.userService.signUp(request)
    }
    suspend fun getUserInfo(id: String) = service.getUserInfo(id)
}