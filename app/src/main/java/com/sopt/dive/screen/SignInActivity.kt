package com.sopt.dive.screen

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.dive.R
import com.sopt.dive.component.LabeledTextField
import com.sopt.dive.component.SignButton
import com.sopt.dive.component.Title
import com.sopt.dive.ui.theme.DiveTheme
import com.sopt.dive.util.IntentKeys

class SignInActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 사용자 입력값
        val idState = mutableStateOf("")
        val passwordState = mutableStateOf("")

        // 회원가입에서 받아온 값
        val registeredIdState = mutableStateOf("")
        val registeredPwState = mutableStateOf("")
        val registeredNicknameState = mutableStateOf("")
        val registeredEtcState = mutableStateOf("")

        // 회원가입 결과 받기
        val signUpLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == RESULT_OK) {
                val data = result.data
                registeredIdState.value = data?.getStringExtra(IntentKeys.ID) ?: ""
                registeredPwState.value = data?.getStringExtra(IntentKeys.PASSWORD) ?: ""
                registeredNicknameState.value = data?.getStringExtra(IntentKeys.NICKNAME) ?: ""
                registeredEtcState.value = data?.getStringExtra(IntentKeys.ETC)?.takeIf { it.isNotBlank() } ?: "응애 못 먹어요"
            }
        }

        setContent {
            DiveTheme {
                SignInScreen(
                    id = idState.value,
                    onIdChange = { idState.value = it },
                    password = passwordState.value,
                    onPasswordChange = { passwordState.value = it },
                    onSignUpClick = {
                        val intent = Intent(this, SignUpActivity::class.java)
                        signUpLauncher.launch(intent)
                    },
                    onSignInClick = {
                        if (idState.value == registeredIdState.value && passwordState.value == registeredPwState.value) {
                            Toast.makeText(this, getString(R.string.toast_login_success), Toast.LENGTH_SHORT).show()

                            val intent = Intent(this, MainActivity::class.java).apply {
                                putExtra(IntentKeys.ID, registeredIdState.value)
                                putExtra(IntentKeys.PASSWORD, registeredPwState.value)
                                putExtra(IntentKeys.NICKNAME, registeredNicknameState.value)
                                putExtra(IntentKeys.ETC, registeredEtcState.value)
                            }
                            startActivity(intent)
                        } else {
                            Toast.makeText(this, getString(R.string.toast_login_fail), Toast.LENGTH_SHORT).show()
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun SignInScreen(
    id: String,
    onIdChange: (String) -> Unit,
    password: String,
    onPasswordChange: (String) -> Unit,
    onSignUpClick: () -> Unit,
    onSignInClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp, vertical = 48.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 타이틀
        Title(
            text = stringResource(R.string.login_title)
        )

        Spacer(modifier = Modifier.height(50.dp))

        // Id, Password
        LabeledTextField(
            label = stringResource(R.string.id_label),
            placeholder = stringResource(R.string.id_hint),
            text = id,
            onValueChange = onIdChange
        )

        Spacer(modifier = Modifier.height(16.dp))

        LabeledTextField(
            label = stringResource(R.string.pw_label),
            placeholder = stringResource(R.string.pw_hint),
            text = password,
            onValueChange = onPasswordChange,
            isPassword = true
        )

        Spacer(modifier = Modifier.weight(1f))

        // 로그인 버튼
        SignButton(
            text = stringResource(R.string.login_button),
            onClick = onSignInClick
        )

        // 회원가입 텍스트
        TextButton(onClick = onSignUpClick) {
            Text(stringResource(R.string.signup_button), color = Color.Gray)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignInScreenPreview() {
    DiveTheme {
        SignInScreen(
            id = "",
            onIdChange = {},
            password = "",
            onPasswordChange = {},
            onSignUpClick = {},
            onSignInClick = {}
        )
    }
}