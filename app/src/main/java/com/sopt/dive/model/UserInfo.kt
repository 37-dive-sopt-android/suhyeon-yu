package com.sopt.dive.model

data class UserInfo(
    var id: String = "",
    var password: String = "",
    var nickname: String = "",
    var etc: String = ""
) {
    fun updateUser(id: String, password: String, nickname: String, etc: String) {
        this.id = id
        this.password = password
        this.nickname = nickname
        this.etc = etc
    }

    fun validateLogin(inputId: String, inputPw: String): Boolean {
        return inputId == id && inputPw == password
    }
}