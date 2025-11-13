package com.sopt.dive.screen.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.sopt.dive.model.HomeListItem

@Composable
fun FriendItem(
    item: HomeListItem.FriendRow,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // 프로필 이미지
        Image(
            painter = painterResource(id = item.profileImage),
            contentDescription = "${item.name}의 프로필 사진",
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column(Modifier.weight(1f)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = item.name,
                    style = MaterialTheme.typography.titleMedium
                )
                if (item.isBirthday) {
                    Spacer(Modifier.width(8.dp))
                    Badge(BadgeType.BIRTHDAY)
                }
            }
            if (!item.statusMessage.isBlank()) {
                Spacer(Modifier.height(4.dp))
                Text(
                    text = item.statusMessage,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                )
            }
        }

        if (!item.music.isNullOrBlank()) {
            Spacer(modifier = Modifier.width(8.dp))
            Badge(BadgeType.MUSIC, text = item.music)
        }
    }
}