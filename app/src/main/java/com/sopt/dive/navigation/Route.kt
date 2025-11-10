package com.sopt.dive.navigation

import kotlinx.serialization.Serializable

interface Route

@Serializable object SignIn : Route
@Serializable object SignUp : Route
@Serializable object Home : Route
@Serializable object Search : Route
@Serializable object MyPage : Route