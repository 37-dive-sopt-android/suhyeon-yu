package com.sopt.dive.data.service

import com.sopt.dive.data.dto.request.RequestSignInDto
import com.sopt.dive.data.dto.request.RequestSignUpDto
import com.sopt.dive.data.dto.response.ResponseLoginDto
import com.sopt.dive.data.dto.response.ResponseSignUpDto
import com.sopt.dive.data.dto.response.ResponseUserInfoDto
import com.sopt.dive.data.dto.response.ServerResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface UserService {

    @POST("auth/login")
    suspend fun login(
        @Body request: RequestSignInDto
    ): ServerResponse<ResponseLoginDto>

    @POST("users")
    suspend fun signUp(
        @Body request: RequestSignUpDto
    ): ServerResponse<ResponseSignUpDto>

    @GET("users/{id}")
    suspend fun getUserInfo(
        @Path("id") id: String
    ): ServerResponse<ResponseUserInfoDto>
}