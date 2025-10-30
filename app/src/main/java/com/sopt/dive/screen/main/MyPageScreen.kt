package com.sopt.dive.screen.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.dive.R
import com.sopt.dive.component.item.InfoItem
import com.sopt.dive.ui.theme.DiveTheme

@Composable
fun MyPageScreen(
    id: String,
    password: String,
    nickname: String,
    etc: String
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 40.dp, vertical = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // profile image
        Image(
            painter = painterResource(id = R.drawable.profile),
            contentDescription = "${nickname}의 프로필 이미지",
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(24.dp))

        // nickname
        Text(
            text = nickname,
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(30.dp))

        // info items
        Column(
            modifier = Modifier
                .align(Alignment.Start)
                .padding(start = 10.dp)
        ) {
            InfoItem(title = stringResource(R.string.id_label), value = id)
            Spacer(modifier = Modifier.height(20.dp))
            InfoItem(title = stringResource(R.string.pw_label), value = password)
            Spacer(modifier = Modifier.height(20.dp))
            InfoItem(title = stringResource(R.string.etc_label), value = etc)
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun MyPageScreenPreview() {
    DiveTheme {
        MyPageScreen(
            id = "ddd",
            password = "ddd",
            nickname = "SUHYEON",
            etc = "0"
        )
    }
}