package com.sopt.dive.navigation

sealed class Route(val route: String) {
    object SignIn : Route("signin")
    object SignUp : Route("signup")
    object Main : Route("main")
}