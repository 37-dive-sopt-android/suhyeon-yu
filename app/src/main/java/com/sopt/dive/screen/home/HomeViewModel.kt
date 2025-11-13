package com.sopt.dive.screen.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.sopt.dive.data.DummyData
import com.sopt.dive.model.HomeListItem
import com.sopt.dive.model.UserInfo

class HomeViewModel : ViewModel() {

    var homeItems by mutableStateOf<List<HomeListItem>>(emptyList())
        private set

    fun loadHomeItems(userInfo: UserInfo) {
        val items = DummyData.buildHomeItems(userInfo)
        homeItems = items
    }
}