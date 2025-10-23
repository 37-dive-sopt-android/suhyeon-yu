package com.sopt.dive.screen

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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

    private lateinit var signUpLauncher: ActivityResultLauncher<Intent>

    private var registeredId: String = ""
    private var registeredPw: String = ""
    private var registeredNickname: String = ""
    private var registeredEtc: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        signUpLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == RESULT_OK) {
                val data = result.data
                registeredId = data?.getStringExtra(IntentKeys.ID) ?: ""
                registeredPw = data?.getStringExtra(IntentKeys.PASSWORD) ?: ""
                registeredNickname = data?.getStringExtra(IntentKeys.NICKNAME) ?: ""
                registeredEtc = data?.getStringExtra(IntentKeys.ETC)?.takeIf { it.isNotBlank() } ?: "응애 못 먹어요"
            }
        }

        setContent {
            DiveTheme {
                var id by rememberSaveable { mutableStateOf("") }
                var password by rememberSaveable { mutableStateOf("") }

                SignInScreen(
                    id = id,
                    onIdChange = { id = it },
                    password = password,
                    onPasswordChange = { password = it },
                    onSignUpClick = {
                        val intent = Intent(this, SignUpActivity::class.java)
                        signUpLauncher.launch(intent)
                    },
                    onSignInClick = {
                        if (registeredId.isNotBlank() && id == registeredId && password == registeredPw) {
                            Toast.makeText(this, getString(R.string.toast_login_success), Toast.LENGTH_SHORT).show()

                            val intent = Intent(this, MainActivity::class.java).apply {
                                putExtra(IntentKeys.ID, registeredId)
                                putExtra(IntentKeys.PASSWORD, registeredPw)
                                putExtra(IntentKeys.NICKNAME, registeredNickname)
                                putExtra(IntentKeys.ETC, registeredEtc)
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
private fun SignInScreen(
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
            .padding(
                horizontal = 40.dp,
                vertical = 40.dp
            ),
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
private fun SignInScreenPreview() {
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