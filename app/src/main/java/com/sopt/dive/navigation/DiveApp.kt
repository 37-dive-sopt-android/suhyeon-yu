package com.sopt.dive.navigation

import android.widget.Toast
import androidx.compose.runtime.Composable
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

    NavHost(
        navController = navController,
        startDestination = Route.SignIn.route
    ) {
        // 로그인 화면
        composable(Route.SignIn.route) {
            SignInScreen(
                onLoginClick = { id, pw ->
                    when {
                        id.isBlank() || pw.isBlank() -> {
                            Toast.makeText(
                                context,
                                context.getString(R.string.toast_login_fail),
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        userInfo.validateLogin(id, pw) -> {
                            navController.navigate(Route.Main.route) {
                                popUpTo(Route.SignIn.route) { inclusive = true }
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
                },
                onSignUpClick = { navController.navigate(Route.SignUp.route) }
            )
        }

        // 회원가입 화면
        composable(Route.SignUp.route) {
            SignUpScreen(
                onSignUpClick = { newId, newPw, newNickname, newEtc ->
                    if (SignUpValidator.validate(context, newId, newPw, newNickname, newEtc)) {
                        userInfo.updateUser(newId, newPw, newNickname, newEtc)
                        navController.popBackStack()
                    }
                }
            )
        }

        // 메인 화면
        composable(Route.Main.route) {
            DiveMainNav(userInfo = userInfo)
        }
    }
}