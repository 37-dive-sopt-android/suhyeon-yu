package com.sopt.dive.screen

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.dive.R
import com.sopt.dive.component.LabeledTextField
import com.sopt.dive.component.SignButton
import com.sopt.dive.component.Title
import com.sopt.dive.ui.theme.DiveTheme
import com.sopt.dive.util.IntentKeys

class SignUpActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiveTheme {
                var id by remember { mutableStateOf("") }
                var password by remember { mutableStateOf("") }
                var nickname by remember { mutableStateOf("") }
                var etc by remember { mutableStateOf("") }

                SignUpScreen(
                    id = id,
                    password = password,
                    nickname = nickname,
                    etc = etc,
                    onIdChange = { id = it },
                    onPasswordChange = { password = it },
                    onNicknameChange = { nickname = it },
                    onEtcChange = { etc = it },
                    onSignUpClick = {
                        when {
                            id.length < 6 || id.length > 10 -> {
                                Toast.makeText(this, getString(R.string.toast_id_fail), Toast.LENGTH_SHORT).show()
                            }
                            password.length < 8 || password.length > 12 -> {
                                Toast.makeText(this, getString(R.string.toast_pw_fail), Toast.LENGTH_SHORT).show()
                            }
                            nickname.isBlank() -> {
                                Toast.makeText(this, getString(R.string.toast_nickname_fail), Toast.LENGTH_SHORT).show()
                            }
                            else -> {
                                val resultIntent = Intent().apply {
                                    putExtra(IntentKeys.ID, id)
                                    putExtra(IntentKeys.PASSWORD, password)
                                    putExtra(IntentKeys.NICKNAME, nickname)
                                    putExtra(IntentKeys.ETC, etc)
                                }
                                setResult(RESULT_OK, resultIntent)
                                Toast.makeText(this, getString(R.string.toast_signup_success), Toast.LENGTH_SHORT).show()
                                finish()
                            }
                        }
                    }
                )
            }
        }
    }
}

@Composable
private fun SignUpScreen(
    id: String,
    password: String,
    nickname: String,
    etc: String,
    onIdChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onNicknameChange: (String) -> Unit,
    onEtcChange: (String) -> Unit,
    onSignUpClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp, vertical = 48.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 타이틀
        Title(stringResource(R.string.signup_title))

        Spacer(modifier = Modifier.height(50.dp))

        // Id, Password, Nickname, etc
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

        Spacer(modifier = Modifier.height(16.dp))

        LabeledTextField(
            label = stringResource(R.string.nickname_label),
            placeholder = stringResource(R.string.nickname_hint),
            text = nickname,
            onValueChange = onNicknameChange
        )

        Spacer(modifier = Modifier.height(16.dp))

        LabeledTextField(
            label = stringResource(R.string.etc_label),
            placeholder = stringResource(R.string.etc_hint),
            text = etc,
            onValueChange = onEtcChange
        )

        Spacer(modifier = Modifier.weight(1f))

        // 회원가입 버튼
        SignButton(
            text = stringResource(R.string.signup_button),
            onClick = onSignUpClick
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SignUpScreenPreview() {
    DiveTheme {
        SignUpScreen(
            id = "",
            password = "",
            nickname = "",
            etc = "",
            onIdChange = {},
            onPasswordChange = {},
            onNicknameChange = {},
            onEtcChange = {},
            onSignUpClick = {}
        )
    }
}