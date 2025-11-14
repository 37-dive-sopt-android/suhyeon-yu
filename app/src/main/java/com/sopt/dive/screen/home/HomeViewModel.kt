package com.sopt.dive.screen.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.dive.data.DummyData
import com.sopt.dive.data.repository.UserRepository
import com.sopt.dive.model.HomeListItem
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val repo = UserRepository()

    var homeItems by mutableStateOf<List<HomeListItem>>(emptyList())
        private set

    fun loadHomeItems(userId: String) {
        viewModelScope.launch {
            val userInfo = repo.getUserInfo(userId)
            homeItems = DummyData.buildHomeItems(userInfo)
        }
    }
}