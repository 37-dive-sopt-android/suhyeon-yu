package com.sopt.dive.screen.signup

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sopt.dive.R
import com.sopt.dive.component.button.BasicButton
import com.sopt.dive.component.text.LabeledTextField
import com.sopt.dive.component.text.Title
import kotlinx.coroutines.flow.collectLatest

@Composable
fun SignUpRoute(
    onSignUpSuccess: () -> Unit
) {
    val viewModel: SignUpViewModel = viewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.signUpSuccess.collectLatest {
            onSignUpSuccess()
        }
    }

    SignUpScreen(
        uiState = uiState,
        onUsernameChange = viewModel::updateUsername,
        onPasswordChange = viewModel::updatePassword,
        onNameChange = viewModel::updateName,
        onEmailChange = viewModel::updateEmail,
        onAgeChange = viewModel::updateAge,
        onSignUpClick = viewModel::signUp
    )
}

@Composable
fun SignUpScreen(
    uiState: SignUpUiState,
    onUsernameChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onNameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onAgeChange: (String) -> Unit,
    onSignUpClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Title(text = stringResource(R.string.signup_title))

        Spacer(Modifier.height(50.dp))

        LabeledTextField(
            label = stringResource(R.string.id_label),
            placeholder = stringResource(R.string.id_hint),
            text = uiState.username,
            onValueChange = onUsernameChange
        )

        Spacer(Modifier.height(16.dp))

        LabeledTextField(
            label = stringResource(R.string.pw_label),
            placeholder = stringResource(R.string.pw_hint),
            text = uiState.password,
            onValueChange = onPasswordChange,
            isPassword = true
        )

        Spacer(Modifier.height(16.dp))

        LabeledTextField(
            label = stringResource(R.string.nickname_label),
            placeholder = stringResource(R.string.nickname_hint),
            text = uiState.name,
            onValueChange = onNameChange
        )

        Spacer(Modifier.height(16.dp))

        LabeledTextField(
            label = stringResource(R.string.email_label),
            placeholder = stringResource(R.string.email_hint),
            text = uiState.email,
            onValueChange = onEmailChange
        )

        Spacer(Modifier.height(16.dp))

        LabeledTextField(
            label = stringResource(R.string.age_label),
            placeholder = stringResource(R.string.age_hint),
            text = uiState.age,
            onValueChange = onAgeChange
        )

        Spacer(modifier = Modifier.weight(1f))

        BasicButton(
            text = stringResource(R.string.signup_button),
            onClick = onSignUpClick
        )
    }
}