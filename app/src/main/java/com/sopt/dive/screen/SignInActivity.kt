package com.sopt.dive.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.sopt.dive.component.LabeledTextField
import com.sopt.dive.component.SignButton
import com.sopt.dive.component.Title


@Composable
fun SignInScreen() {
    var id by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("")}

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp, vertical = 48.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 타이틀
        Title(
            text = "Welcome To SOPT"
        )

        Spacer(modifier = Modifier.height(50.dp))

        // Id, Password
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

        Spacer(modifier = Modifier.height(400.dp))

        // 로그인 버튼
        SignButton (
            text = "Welcome To SOPT",
            onClick = { /* 로그인 로직 */}
        )

        // 회원가입 텍스트
        TextButton(onClick = { /* 회원가입 화면 이동 */}) {
            Text("회원가입하기", color = Color.Gray)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun SignInScreenPreview() {
    SignInScreen()
}