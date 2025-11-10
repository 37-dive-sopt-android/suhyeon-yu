package com.sopt.dive.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.sopt.dive.model.UserInfo
import com.sopt.dive.screen.home.HomeScreen
import com.sopt.dive.screen.mypage.MyPageScreen
import com.sopt.dive.screen.search.SearchScreen
import com.sopt.dive.screen.signin.SignInScreen
import com.sopt.dive.screen.signup.SignUpScreen

fun NavGraphBuilder.diveNavGraph(
    navController: NavController,
    userInfo: UserInfo
) {
    composable<SignIn> {
        SignInScreen(
            userInfo = userInfo,
            onLoginSuccess = {
                navController.navigate(Home) {
                    popUpTo(SignIn) { inclusive = true }
                }
            },
            onSignUpClick = {
                navController.navigate(SignUp)
            }
        )
    }

    composable<SignUp> {
        SignUpScreen(
            userInfo = userInfo,
            onSignUpSuccess = {
                navController.popBackStack()
            }
        )
    }

    composable<Home> {
        HomeScreen(
            userInfo = userInfo
        )
    }

    composable<Search> {
        SearchScreen()
    }

    composable<MyPage> {
        MyPageScreen(
            id = userInfo.id,
            password = userInfo.password,
            nickname = userInfo.nickname,
            etc = userInfo.etc
        )
    }
}