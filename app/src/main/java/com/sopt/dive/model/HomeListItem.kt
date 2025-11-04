package com.sopt.dive.model

import androidx.annotation.DrawableRes

sealed interface HomeListItem {
    data class SectionHeader(val title: String) : HomeListItem

    data class MyProfile(
        val name: String,
        val statusMessage: String,
        @DrawableRes val profileImage: Int
    ) : HomeListItem

    data class FriendRow(
        val name: String,
        val statusMessage: String,
        @DrawableRes val profileImage: Int,
        val music: String? = null,
        val isBirthday: Boolean = false
    ) : HomeListItem
}