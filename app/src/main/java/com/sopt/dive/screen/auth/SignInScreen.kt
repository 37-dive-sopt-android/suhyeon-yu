package com.sopt.dive.screen.auth

import android.widget.Toast
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
import com.sopt.dive.navigation.Route
import com.sopt.dive.ui.theme.DiveTheme

@Composable
fun SignInScreen(
    navController: NavController,
    userInfo: UserInfo
) {
    val context = LocalContext.current
    var id by rememberSaveable { mutableStateOf("")}
    var password by rememberSaveable { mutableStateOf("")}

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Title(text = stringResource(R.string.login_title))

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
            text = password,
            onValueChange = { password = it },
            isPassword = true
        )

        Spacer(modifier = Modifier.weight(1f))

        BasicButton(
            text = stringResource(R.string.login_button),
            onClick = {
                when {
                    id.isBlank() || password.isBlank() -> {
                        Toast.makeText(
                            context,
                            context.getString(R.string.toast_login_fail),
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    userInfo.validateLogin(id, password) -> {
                        navController.navigate(Route.Main) {
                            popUpTo<Route.SignIn> { inclusive = true }
                        }
                    }

                    else -> {
                        Toast.makeText(
                            context,
                            context.getString(R.string.toast_login_fail),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        )

        TextButton(onClick = {
            navController.navigate(Route.SignUp)
        }) {
            Text(
                text = stringResource(R.string.signup_button),
                color = Color.Gray
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SignInScreenPreview() {
    DiveTheme {
    }
}