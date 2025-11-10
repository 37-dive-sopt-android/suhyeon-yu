package com.sopt.dive.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Route {
    @Serializable
    data object SignIn : Route()

    @Serializable
    data object SignUp : Route()

    @Serializable
    data object Main : Route()
}