package com.sopt.dive.data.dto.response

import kotlinx.serialization.Serializable

@Serializable
data class ResponseUserInfoDto(
    val success: Boolean,
    val code: String,
    val message: String,
    val data: UserInfoData?
)

@Serializable
data class UserInfoData(
    val id: Long,
    val username: String,
    val name: String,
    val email: String,
    val age: Int,
    val status: String
)