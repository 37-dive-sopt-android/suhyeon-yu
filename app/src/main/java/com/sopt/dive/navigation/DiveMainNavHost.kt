package com.sopt.dive.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sopt.dive.model.UserInfo
import com.sopt.dive.screen.main.HomeScreen
import com.sopt.dive.screen.main.MyPageScreen
import com.sopt.dive.screen.main.SearchScreen

@Composable
fun DiveMainNavHost(navController: androidx.navigation.NavHostController, userInfo: UserInfo, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = TabItem.Home.route,
        modifier = modifier
    ) {
        composable(TabItem.Home.route) { HomeScreen(userInfo = userInfo) }
        composable(TabItem.Search.route) { SearchScreen() }
        composable(TabItem.My.route) {
            MyPageScreen(
                id = userInfo.id,
                password = userInfo.password,
                nickname = userInfo.nickname,
                etc = userInfo.etc
            )
        }
    }
}