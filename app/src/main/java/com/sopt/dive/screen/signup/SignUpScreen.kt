package com.sopt.dive.screen.signup

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sopt.dive.R
import com.sopt.dive.component.button.BasicButton
import com.sopt.dive.component.text.LabeledTextField
import com.sopt.dive.component.text.Title

@Composable
fun SignUpScreen(
    onSignUpSuccess: () -> Unit
) {
    val viewModel: SignUpViewModel = viewModel()
    val signUpState = viewModel.signUpState.value

    if (signUpState != null) {
        onSignUpSuccess()
    }

    var username by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var name by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var age by rememberSaveable { mutableStateOf("") }

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
            text = username,
            onValueChange = { username = it }
        )

        Spacer(modifier = Modifier.height(16.dp))

        LabeledTextField(
            label = stringResource(R.string.pw_label),
            placeholder = stringResource(R.string.pw_hint),
            text = password,
            onValueChange = { password = it },
            isPassword = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        LabeledTextField(
            label = stringResource(R.string.nickname_label),
            placeholder = stringResource(R.string.nickname_hint),
            text = name,
            onValueChange = { name = it }
        )

        Spacer(modifier = Modifier.height(16.dp))

        LabeledTextField(
            label = stringResource(R.string.email_label),
            placeholder = stringResource(R.string.email_hint),
            text = email,
            onValueChange = { email = it }
        )

        Spacer(modifier = Modifier.height(16.dp))

        LabeledTextField(
            label = stringResource(R.string.age_label),
            placeholder = stringResource(R.string.age_hint),
            text = age,
            onValueChange = { age = it }
        )

        Spacer(modifier = Modifier.weight(1f))

        BasicButton(
            text = stringResource(R.string.signup_button),
            onClick = {
                val parseAge = age.toIntOrNull()

                if (username.isBlank() ||
                    password.isBlank() ||
                    name.isBlank() ||
                    email.isBlank() ||
                    parseAge == null
                    ) {
                    return@BasicButton
                }
                viewModel.signUp(
                    username = username,
                    password = password,
                    name = name,
                    email = email,
                    age = parseAge
                )
            }
        )
    }
}