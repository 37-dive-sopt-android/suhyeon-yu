package com.sopt.dive.screen

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext
import com.sopt.dive.component.LabeledTextField
import com.sopt.dive.component.SignButton
import com.sopt.dive.component.Title


@Composable
fun SignUpScreen() {
    var id by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("")}
    var nickname by remember {mutableStateOf("")}
    var etc by remember {mutableStateOf("")}

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp, vertical = 48.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 타이틀
        Title("SIGN UP")

        Spacer(modifier = Modifier.height(50.dp))

        // Id, Password, Nickname, etc
        LabeledTextField(
            label = "ID",
            placeholder = "아이디를 입력해주세요",
            text = id,
            onValueChange = { id = it}
        )

        Spacer(modifier = Modifier.height(16.dp))

        LabeledTextField(
            label = "PW",
            placeholder = "비밀번호를 입력해주세요",
            text = password,
            onValueChange = {password = it},
            isPassword = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        LabeledTextField(
            label = "NICKNAME",
            placeholder = "닉네임을 입력해주세요",
            text = nickname,
            onValueChange = {nickname = it}
        )

        Spacer(modifier = Modifier.height(16.dp))

        LabeledTextField(
            label = "주량",
            placeholder = "소주 주량을 입력해주세요",
            text = etc,
            onValueChange = {etc = it}
        )

        Spacer(modifier = Modifier.height(250.dp))

        // 회원가입 버튼
        SignButton (
            text = "회원가입하기",
            onClick = {
                when {
                    id.length < 6 || id.length > 10 -> {
                        Toast.makeText(context, "아이디는 6-10자여야 합니다.", Toast.LENGTH_SHORT).show()
                    }
                    password.length <8 || password.length > 12 -> {
                        Toast.makeText(context, "비밀번호는 8-12자여야 합니다.", Toast.LENGTH_SHORT).show()
                    }
                    nickname.isBlank() -> {
                        Toast.makeText(context, "닉네임을 입력해주세요.", Toast.LENGTH_SHORT).show()
                    }
                    else -> {
                        /* 회원가입 성공 */
                    }
                }
                }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SignUpScreenPreview() {
    SignUpScreen()
}