package com.sopt.dive.screen

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
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

@Composable
fun SignUpScreen(
    id: String,
    onIdChange: (String) -> Unit,
    password: String,
    onPasswordChange: (String) -> Unit,
    nickname: String,
    onNicknameChange: (String) -> Unit,
    etc: String,
    onEtcChange: (String) -> Unit,
    onSignUpClick: (String, String, String, String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 40.dp, vertical = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Title(stringResource(R.string.signup_title))

        Spacer(modifier = Modifier.height(50.dp))

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

        SignButton(
            text = stringResource(R.string.signup_button),
            onClick = {
                onSignUpClick(id, password, nickname, etc)
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SignUpScreenPreview() {
    DiveTheme {
        SignUpScreen(
            id = "",
            onIdChange = {},
            password = "",
            onPasswordChange = {},
            nickname = "",
            onNicknameChange = {},
            etc = "",
            onEtcChange = {},
            onSignUpClick = {_, _, _, _ -> }
        )
    }
}