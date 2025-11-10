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
        startDestination = Route.SignIn
    ) {
        // 로그인 화면
        composable<Route.SignIn> {
            SignInScreen(
                navController = navController,
                userInfo = userInfo
            )
        }

        // 회원가입 화면
        composable<Route.SignUp> {
            SignUpScreen(
                navController = navController,
                userInfo = userInfo
            )
        }

        // 메인 화면 NavController 전달
        composable<Route.Main> {
            DiveMainNav(
                userInfo = userInfo
            )
        }
    }
}