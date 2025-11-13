package com.sopt.dive.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.sopt.dive.component.navigation.BottomNavBar
import com.sopt.dive.screen.home.navigation.Home
import com.sopt.dive.screen.mypage.navigation.MyPage
import com.sopt.dive.screen.search.navigation.Search
import com.sopt.dive.screen.signin.navigation.SignIn

@Composable
fun DiveApp() {
    val navController = rememberNavController()
    val currentDestination = navController.currentBackStackEntryAsState().value?.destination

    val showBottomBar = currentDestination?.hasRoute(Home::class) == true ||
            currentDestination?.hasRoute(Search::class) == true ||
            currentDestination?.hasRoute(MyPage::class) == true

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
            diveNavGraph(navController)
        }
    }
}