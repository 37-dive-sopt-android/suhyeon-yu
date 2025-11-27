package com.sopt.dive.screen.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sopt.dive.R
import com.sopt.dive.component.text.SectionTitle
import com.sopt.dive.model.HomeListItem
import com.sopt.dive.screen.home.component.FriendItem
import com.sopt.dive.screen.home.component.MyProfileItem
import com.sopt.dive.ui.theme.DiveTheme

@Composable
fun HomeRoute(
    userId: String,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.loadHomeItems(userId)
    }

    val items = viewModel.homeItems

    HomeScreen(
        items = items,
        modifier = modifier
    )
}

@Composable
fun HomeScreen(
    items: List<HomeListItem>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        itemsIndexed(
            items = items,
            key = { index, item -> "$index-${item::class.simpleName}" },
            contentType = { _, item -> item::class.simpleName }
        ) { index, item ->
            when (item) {
                is HomeListItem.SectionHeader -> {
                    SectionTitle(title = item.title)
                }

                is HomeListItem.MyProfile -> {
                    MyProfileItem(item = item)
                    HorizontalDivider(thickness = 0.5.dp)
                }

                is HomeListItem.FriendRow -> {
                    FriendItem(item = item)
                    if (index != items.lastIndex)
                        HorizontalDivider(thickness = 0.5.dp)
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun HomeScreenPreview() {
    DiveTheme {
        HomeScreen(
            items = listOf(
                HomeListItem.SectionHeader(title = "내 프로필"),
                HomeListItem.MyProfile(name = "수현", statusMessage = "안뇽", R.drawable.profile),
                HomeListItem.SectionHeader(title = "친구"),
                HomeListItem.FriendRow(name = "나현", statusMessage = "하이루", R.drawable.profile),
                HomeListItem.FriendRow(name = "나현", statusMessage = "하이루", R.drawable.profile),
                HomeListItem.FriendRow(name = "나현", statusMessage = "하이루", R.drawable.profile),
                HomeListItem.FriendRow(name = "나현", statusMessage = "하이루", R.drawable.profile),
                HomeListItem.FriendRow(name = "나현", statusMessage = "하이루", R.drawable.profile),
                HomeListItem.FriendRow(name = "나현", statusMessage = "하이루", R.drawable.profile),
                HomeListItem.FriendRow(name = "나현", statusMessage = "하이루", R.drawable.profile),
                HomeListItem.FriendRow(name = "나현", statusMessage = "하이루", R.drawable.profile),
                HomeListItem.FriendRow(name = "나현", statusMessage = "하이루", R.drawable.profile),
                HomeListItem.FriendRow(name = "나현", statusMessage = "하이루", R.drawable.profile),
                HomeListItem.FriendRow(name = "나현", statusMessage = "하이루", R.drawable.profile)
                )
        )
    }
}