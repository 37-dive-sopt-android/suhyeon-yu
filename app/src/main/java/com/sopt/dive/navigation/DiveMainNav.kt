package com.sopt.dive.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.sopt.dive.component.navigation.BottomNavBar
import com.sopt.dive.model.UserInfo

@Composable
fun DiveMainNav(userInfo: UserInfo) {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavBar(navController = navController)
        }
    ) { innerPadding ->
        DiveMainNavHost(navController = navController, userInfo = userInfo, modifier = Modifier.padding(innerPadding))
    }
}
