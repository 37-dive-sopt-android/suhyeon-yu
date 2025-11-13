package com.sopt.dive.data.dto.response

import kotlinx.serialization.Serializable

@Serializable
data class ServerResponse<T>(
    val success: Boolean,
    val code: String,
    val message: String,
    val data: T?
)