package com.sopt.dive.screen

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sopt.dive.R
import com.sopt.dive.model.UserInfo
import com.sopt.dive.ui.theme.DiveTheme
import com.sopt.dive.util.SignUpValidator

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            DiveTheme {
                val navController = rememberNavController()
                val userInfo = remember { UserInfo() }

                var loginId by rememberSaveable { mutableStateOf("") }
                var loginPw by rememberSaveable { mutableStateOf("") }

                val context = LocalContext.current

                NavHost(navController = navController, startDestination = "signin") {
                    composable("signin") {
                        SignInScreen(
                            id = loginId,
                            onIdChange = { loginId = it },
                            password = loginPw,
                            onPasswordChange = { loginPw = it },
                            onLoginClick = {
                                // validate logic
                                if (loginId.isBlank() || loginPw.isBlank()) {
                                    Toast.makeText(
                                        context,
                                        context.getString(R.string.toast_login_fail),
                                        Toast.LENGTH_SHORT
                                    ).show()
                                } else if (userInfo.validateLogin(loginId, loginPw)) {
                                    navController.navigate("mypage")
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
                                if (SignUpValidator.validate(
                                        context,
                                        newId,
                                        newPw,
                                        newNickname,
                                        newEtc
                                    )
                                ) {
                                    userInfo.updateUser(
                                        id = newId,
                                        password = newPw,
                                        nickname = newNickname,
                                        etc = newEtc
                                    )
                                    navController.popBackStack()
                                }
                            }
                        )
                    }

                    composable("mypage") {
                        MyPageScreen(
                            id = userInfo.id,
                            password = userInfo.password,
                            nickname = userInfo.nickname,
                            etc = userInfo.etc
                        )
                    }
                }
            }
        }
    }
}