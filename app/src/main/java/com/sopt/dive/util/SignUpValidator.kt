package com.sopt.dive.util

import android.content.Context
import android.widget.Toast
import com.sopt.dive.R

object SignUpValidator {
    fun validate(
        context: Context,
        id: String,
        password: String,
        nickname: String,
        etc: String
    ): Boolean {
        return when {
            id.length < 6 || id.length > 10 -> {
                Toast.makeText(context, context.getString(R.string.toast_id_fail), Toast.LENGTH_SHORT).show()
                false
            }
            password.length < 8 || password.length > 12 -> {
                Toast.makeText(context, context.getString(R.string.toast_pw_fail), Toast.LENGTH_SHORT).show()
                false
            }
            nickname.isBlank() -> {
                Toast.makeText(context, context.getString(R.string.toast_nickname_fail), Toast.LENGTH_SHORT).show()
                false
            }
            etc.isBlank() -> true
            else -> true
        }
    }
}