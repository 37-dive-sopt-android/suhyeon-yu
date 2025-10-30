package com.sopt.dive.screen.main

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.dive.R
import com.sopt.dive.component.item.FriendItem
import com.sopt.dive.component.text.SectionTitle
import com.sopt.dive.data.DummyData
import com.sopt.dive.model.UserInfo
import com.sopt.dive.ui.theme.DiveTheme

data class Friend(
    val name: String,
    val statusMessage: String,
    val profileImage: Int
)

@Composable
fun HomeScreen(userInfo: UserInfo) {
    val myProfile = Friend(
        name = userInfo.nickname,
        statusMessage = "저는 ${userInfo.etc}병 마실 수 잇어요.",
        profileImage = R.drawable.profile
    )

    val friends = DummyData.friends

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        item {
            SectionTitle(title = "내 프로필")
            FriendItem(friend = myProfile)
            HorizontalDivider(thickness = 0.5.dp)
        }

        item {
            Spacer(modifier = Modifier.height(8.dp))
            SectionTitle(title = "친구 ${friends.size}명")
        }

        items(friends) { friend ->
            FriendItem(friend = friend)
            HorizontalDivider(thickness = 0.5.dp)
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun HomeScreenPreview() {
    DiveTheme {
        HomeScreen(
            userInfo = UserInfo(
                id = "ddd",
                password = "ddd",
                nickname = "SUHYEON",
                etc = "0"
            )
        )
    }
}