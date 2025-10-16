package com.sopt.dive.screen

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.sopt.dive.component.LabeledTextField
import com.sopt.dive.component.SignButton
import com.sopt.dive.component.Title
import com.sopt.dive.ui.theme.DiveTheme

class SignInActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiveTheme {
                SignInScreen()
            }
        }
    }
}

@Composable
fun SignInScreen() {
    val context = LocalContext.current

    // 사용자 입력값
    var id by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("")}

    // 회원가입에서 받아온 값
    var registeredId by remember { mutableStateOf("")}
    var registeredPw by remember { mutableStateOf("")}

    // 회원가입 결과 받기
    val signUpLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result -> if (result.resultCode == Activity.RESULT_OK) {
        val data = result.data
        registeredId = data?.getStringExtra("id") ?: ""
        registeredPw = data?.getStringExtra("password") ?: ""
        }
    }

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

        Spacer(modifier = Modifier.weight(1f))

        // 로그인 버튼
        SignButton (
            text = "Welcome To SOPT",
            onClick = {
                if ( id == registeredId && password == registeredPw ) {
                    Toast.makeText(context, "로그인에 성공했습니다", Toast.LENGTH_SHORT).show()

                    val intent = Intent(context, MainActivity::class.java)
                    context.startActivity(intent)
                }
                else {
                    Toast.makeText(context, "아이디 또는 비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show()
                }
            }
        )

        // 회원가입 텍스트
        TextButton(onClick = {
            val intent = Intent(context, SignUpActivity::class.java)
            signUpLauncher.launch(intent)
        }) {
            Text("회원가입하기", color = Color.Gray)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun SignInScreenPreview() {
    SignInScreen()
}