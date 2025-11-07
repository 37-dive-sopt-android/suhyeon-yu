package com.sopt.dive.data

import com.sopt.dive.R
import com.sopt.dive.model.HomeListItem
import com.sopt.dive.model.UserInfo

object DummyData {

    data class FriendRaw(
        val name: String,
        val status: String?,
        val music: String? = null,
        val isBirthday: Boolean = false
    )

    val friends: List<FriendRaw> = listOf(
        FriendRaw("가현","안녕", music = "Blue Valentine - NMIXX", isBirthday = true),
        FriendRaw("나현","앙뇽"),
        FriendRaw("다현","하이염", music = "타임캡슐 - 다비치"),
        FriendRaw("라현","바이염", isBirthday = true),
        FriendRaw("가현","안녕", music = "GOLDEN - HUNTRIX", isBirthday = true),
        FriendRaw("나현","앙뇽"),
        FriendRaw("다현","하이염", music = "Good Goodbye - HWASA"),
        FriendRaw("라현","바이염", isBirthday = true),
        FriendRaw("가현","안녕", music = "Drowning - WOODZ", isBirthday = true),
        FriendRaw("나현","앙뇽"),
        FriendRaw("다현","하이염", music = "뛰어 - BLACKPINK"),
        FriendRaw("라현","바이염", isBirthday = true),
    )

    fun buildHomeItems(user: UserInfo) : List<HomeListItem> {
        val result = mutableListOf<HomeListItem>()

        // My Profile Section
        result += HomeListItem.SectionHeader("내 프로필")
        result += HomeListItem.MyProfile(
            name = user.nickname,
            statusMessage = "저는 ${user.etc}병 마실 수 잇어요.",
            profileImage = R.drawable.profile
        )
        // Friends Section
        result += HomeListItem.SectionHeader("친구 ${friends.size}명")
        result += friends.map { raw ->
            HomeListItem.FriendRow(
                name = raw.name,
                statusMessage = raw.status?:"",
                profileImage = R.drawable.profile,
                music = raw.music,
                isBirthday = raw.isBirthday
            )
        }
        return result
    }
}