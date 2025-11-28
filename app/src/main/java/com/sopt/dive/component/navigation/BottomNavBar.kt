package com.sopt.dive.component.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.sopt.dive.navigation.NavBarDestination
import com.sopt.dive.screen.home.navigation.Home
import com.sopt.dive.theme.DiveTheme

@Composable
fun BottomNavBar(navController: NavHostController) {
    val tabs = listOf(
        NavBarDestination.HOME,
        NavBarDestination.SEARCH,
        NavBarDestination.MY
    )
    val currentDestination = navController.currentBackStackEntryAsState().value?.destination

    NavigationBar(
        containerColor = Color.White,
        tonalElevation = 4.dp,
        modifier = Modifier
            .height(80.dp)
            .shadow(4.dp)
    ) {
        tabs.forEach { tab ->
            val selected = currentDestination?.hasRoute(tab.route::class) == true
            NavigationBarItem(
                selected = selected,
                onClick = {
                    navController.navigate(tab.route) {
                        popUpTo<Home> { saveState = true }
                        // <Home>은 Home destination을 popUpTo 기준점으로 삼겠다는 뜻
                        // = Home을 스택의 root로 삼고 그 아래 쌓인 화면은 전부 제거
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Image(
                        painter = painterResource(id = tab.iconRes),
                        contentDescription = tab.label,
                        modifier = Modifier
                            .size( if (selected) 28.dp else 24.dp)
                    )
                },
                alwaysShowLabel = true,
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.Transparent
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun BottomNavBarPreview() {
    val navController = rememberNavController()
    DiveTheme {
        BottomNavBar(navController = navController)
    }
}
