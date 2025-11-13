package com.sopt.dive.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.sopt.dive.screen.home.HomeScreen
import com.sopt.dive.screen.home.navigation.Home
import com.sopt.dive.screen.mypage.MyPageScreen
import com.sopt.dive.screen.mypage.navigation.MyPage
import com.sopt.dive.screen.search.SearchScreen
import com.sopt.dive.screen.search.navigation.Search
import com.sopt.dive.screen.signin.SignInScreen
import com.sopt.dive.screen.signin.navigation.SignIn
import com.sopt.dive.screen.signup.SignUpScreen
import com.sopt.dive.screen.signup.navigation.SignUp

fun NavGraphBuilder.diveNavGraph(
    navController: NavController,
) {
    var userId = ""

    composable<SignIn> {
        SignInScreen(
            onLoginSuccess = { returnId ->
                userId = returnId // 서버에서 받은 id 저장
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
            onSignUpSuccess = {
                navController.popBackStack()
            }
        )
    }

    composable<Home> {
        HomeScreen(
            userId = userId
        )
    }

    composable<Search> {
        SearchScreen()
    }

    composable<MyPage> {
        MyPageScreen(
            id = userId
        )
    }
}