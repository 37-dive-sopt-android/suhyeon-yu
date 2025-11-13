package com.sopt.dive.data.dto.response

import kotlinx.serialization.Serializable

@Serializable
data class ResponseLoginDto(
    val success: Boolean,
    val code: String,
    val message: String,
    val data: LoginData
)

@Serializable
data class LoginData(
    val userId: Long,
    val message: String
)