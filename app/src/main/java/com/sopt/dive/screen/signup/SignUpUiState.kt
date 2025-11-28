package com.sopt.dive.screen.signup

data class SignUpUiState(
    val username: String = "",
    val password: String = "",
    val name: String = "",
    val email: String = "",
    val age: String = "",
    val isLoading: Boolean = false
)