package com.sopt.dive.navigation

import androidx.annotation.DrawableRes
import com.sopt.dive.R
import com.sopt.dive.screen.home.navigation.Home
import com.sopt.dive.screen.mypage.navigation.MyPage
import com.sopt.dive.screen.search.navigation.Search

enum class NavBarDestination(
    val route: Route,
    @DrawableRes val iconRes: Int,
    val label: String
) {
    HOME(
        route = Home,
        iconRes = R.drawable.ic_home,
        label = "Home"),
    SEARCH(
        route = Search,
        iconRes = R.drawable.ic_search,
        label = "Search"),
    MY(
        route = MyPage,
        iconRes = R.drawable.ic_my,
        label = "My");
}


//탭 구성이 고정되어 있고 단순한 데이터만 갖는 경우
//enum class
//탭마다 고유한 데이터나 로직이 필요한 경우
//sealed class
//단순히 타입만 구분하고 싶을 때 (공통 속성 거의 없음)
//sealed interface
