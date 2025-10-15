package com.sopt.dive

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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

@Composable
fun LabeledTextField(
    label: String,
    placeholder: String,
    text: String,
    onValueChange: (String) -> Unit,
    isPassword: Boolean = false
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = label,
            fontSize = 20.sp,
            color = Color.Black
        )
        TextField(
            value = text,
            onValueChange = onValueChange,
            placeholder = { Text(placeholder, color = Color.Gray, fontSize = 15.sp)},
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth(),
            textStyle = LocalTextStyle.current.copy(
                fontSize = 15.sp,
                color = Color.Black
            ),
            visualTransformation = if (isPassword) PasswordVisualTransformation()
                else VisualTransformation.None,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Black,
                unfocusedIndicatorColor = Color.Black
            )
        )
    }
}

@Composable
fun SignButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(25.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFFFF0053),
            contentColor = Color.White
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
    ) {
        Text(text)
    }
}

@Composable
fun Title(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleLarge,
        color = Color.Black,
        textAlign = TextAlign.Center,
        fontSize = 30.sp,
        fontWeight = FontWeight.Bold
    )
}

@Preview(showBackground = true)
@Composable
fun SignInScreenPreview() {
    SignInScreen()
}