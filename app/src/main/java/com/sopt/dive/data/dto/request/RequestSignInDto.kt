package com.sopt.dive.data.dto.request

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class RequestSignInDto(
    @SerialName("username")
    val username: String,
    @SerialName("password")
    val password: String,
)