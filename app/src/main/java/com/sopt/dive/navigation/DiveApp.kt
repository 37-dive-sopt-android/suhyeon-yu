package com.sopt.dive.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.sopt.dive.component.navigation.BottomNavBar
import com.sopt.dive.model.UserInfo

@Composable
fun DiveApp(userInfo: UserInfo) {
    val navController = rememberNavController()
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

    val showBottomBar = currentRoute?.contains("Home") == true ||
            currentRoute?.contains("Search") == true ||
            currentRoute?.contains("MyPage") == true

    Scaffold(
        bottomBar = {
            if (showBottomBar) BottomNavBar(navController)
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = SignIn,
            modifier = Modifier.padding(innerPadding)
        ) {
            diveNavGraph(navController, userInfo)
        }
    }
}