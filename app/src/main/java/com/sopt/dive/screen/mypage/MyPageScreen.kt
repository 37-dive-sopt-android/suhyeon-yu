package com.sopt.dive.screen.mypage

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sopt.dive.R
import com.sopt.dive.screen.mypage.component.InfoItem
import com.sopt.dive.theme.DiveTheme

@Composable
fun MyPageScreen(
    id: String
) {
    val viewModel: MyPageViewModel = viewModel()
    val response = viewModel.userInfoState.value
    val user = response?.data  // data만

    // 화면 진입 시 서버 데이터 요청
    LaunchedEffect(Unit) {
        viewModel.loadUserInfo(id)
    }

    // 로딩 중
    if (user == null) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = stringResource(R.string.info_loading))
        }
        return
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 프로필 이미지
        Image(
            painter = painterResource(id = R.drawable.profile),
            contentDescription = "${user.name}의 프로필 이미지",
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(24.dp))

        // 유저 이름
        Text(
            text = user.name,
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(30.dp))

        // 항목 리스트
        Column(
            modifier = Modifier
                .align(Alignment.Start)
                .padding(start = 10.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            InfoItem(title = stringResource(R.string.id_label), value = user.username)
            InfoItem(title = stringResource(R.string.email_label), value = user.email)
            InfoItem(title = stringResource(R.string.age_label), value = user.age.toString())
            InfoItem(title = stringResource(R.string.status_label), value = user.status)
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun MyPageScreenPreview() {
    DiveTheme {
        MyPageScreen(
            id = "1"
        )
    }
}