package com.sopt.dive.screen.auth

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.sopt.dive.R
import com.sopt.dive.component.button.BasicButton
import com.sopt.dive.component.text.LabeledTextField
import com.sopt.dive.component.text.Title
import com.sopt.dive.model.UserInfo
import com.sopt.dive.ui.theme.DiveTheme
import com.sopt.dive.util.SignUpValidator

@Composable
fun SignUpScreen(
    navController: NavController,
    userInfo: UserInfo
) {
    val context = LocalContext.current

    var id by rememberSaveable { mutableStateOf("") }
    var pw by rememberSaveable { mutableStateOf("") }
    var nickname by rememberSaveable { mutableStateOf("") }
    var etc by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Title(text = stringResource(R.string.signup_title))

        Spacer(modifier = Modifier.height(50.dp))

        LabeledTextField(
            label = stringResource(R.string.id_label),
            placeholder = stringResource(R.string.id_hint),
            text = id,
            onValueChange = { id = it }
        )

        Spacer(modifier = Modifier.height(16.dp))

        LabeledTextField(
            label = stringResource(R.string.pw_label),
            placeholder = stringResource(R.string.pw_hint),
            text = pw,
            onValueChange = { pw = it },
            isPassword = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        LabeledTextField(
            label = stringResource(R.string.nickname_label),
            placeholder = stringResource(R.string.nickname_hint),
            text = nickname,
            onValueChange = { nickname = it }
        )

        Spacer(modifier = Modifier.height(16.dp))

        LabeledTextField(
            label = stringResource(R.string.etc_label),
            placeholder = stringResource(R.string.etc_hint),
            text = etc,
            onValueChange = { etc = it }
        )

        Spacer(modifier = Modifier.weight(1f))

        BasicButton(
            text = stringResource(R.string.signup_button),
            onClick = {
                if (SignUpValidator.validate(context, id, pw, nickname, etc)) {
                    userInfo.updateUser(id, pw, nickname, etc)
                    navController.popBackStack() // 회원가입 완료 후 로그인 화면으로 복귀
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SignUpScreenPreview() {
    DiveTheme {
    }
}