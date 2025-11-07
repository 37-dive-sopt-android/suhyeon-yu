package com.sopt.dive.navigation

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sopt.dive.R
import com.sopt.dive.model.UserInfo
import com.sopt.dive.screen.auth.SignInScreen
import com.sopt.dive.screen.auth.SignUpScreen
import com.sopt.dive.util.SignUpValidator

@Composable
fun DiveApp(userInfo: UserInfo) {
    val navController = rememberNavController()
    val context = LocalContext.current

    var loginId by rememberSaveable { mutableStateOf("") }
    var loginPw by rememberSaveable { mutableStateOf("") }

    NavHost(navController = navController, startDestination = "signin") {
        composable("signin") {
            SignInScreen(
                id = loginId,
                onIdChange = { loginId = it },
                password = loginPw,
                onPasswordChange = { loginPw = it },
                onLoginClick = {
                    if (loginId.isBlank() || loginPw.isBlank()) {
                        Toast.makeText(
                            context,
                            context.getString(R.string.toast_login_fail),
                            Toast.LENGTH_SHORT
                        ).show()
                    } else if (userInfo.validateLogin(loginId, loginPw)) {
                        navController.navigate("main") { popUpTo("signin") { inclusive = true } }
                    } else {
                        Toast.makeText(
                            context,
                            context.getString(R.string.toast_login_fail),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                },
                onSignUpClick = { navController.navigate("signup") }
            )
        }

        composable("signup") {
            var id by rememberSaveable { mutableStateOf("") }
            var pw by rememberSaveable { mutableStateOf("") }
            var nickname by rememberSaveable { mutableStateOf("") }
            var etc by rememberSaveable { mutableStateOf("") }

            SignUpScreen(
                id = id,
                onIdChange = { id = it },
                password = pw,
                onPasswordChange = { pw = it },
                nickname = nickname,
                onNicknameChange = { nickname = it },
                etc = etc,
                onEtcChange = { etc = it },
                onSignUpClick = { newId, newPw, newNickname, newEtc ->
                    if (SignUpValidator.validate(context, newId, newPw, newNickname, newEtc)) {
                        userInfo.updateUser(newId, newPw, newNickname, newEtc)
                        navController.popBackStack()
                    }
                }
            )
        }

        composable("main") {
            DiveMainNav(userInfo = userInfo)
        }
    }
}