package com.sopt.dive.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.sopt.dive.model.UserInfo
import com.sopt.dive.screen.main.HomeScreen
import com.sopt.dive.screen.main.MyPageScreen
import com.sopt.dive.screen.main.SearchScreen

@Composable
fun DiveMainNav(userInfo: UserInfo) {
    val navController = rememberNavController()
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

    val tabs = listOf("home", "search", "my")

    Scaffold(
        bottomBar = {
            NavigationBar {
                tabs.forEach { tab ->
                    NavigationBarItem(
                        selected = currentRoute == tab,
                        onClick = {
                            navController.navigate(tab) {
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        label = { Text(tab.replaceFirstChar { it.uppercase() }) },
                        icon = {}
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("home") { HomeScreen(userInfo = userInfo) }
            composable("search") { SearchScreen() }
            composable("my") {
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