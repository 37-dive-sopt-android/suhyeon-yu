package com.sopt.dive.navigation

import com.sopt.dive.R

sealed class TabItem(val route: String, val iconRes: Int, val label: String) {
    object Home : TabItem("home", R.drawable.ic_home, "Home")
    object Search : TabItem("search", R.drawable.ic_search, "Search")
    object My : TabItem("my", R.drawable.ic_my, "My")
}