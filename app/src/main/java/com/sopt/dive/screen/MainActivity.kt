package com.sopt.dive.screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
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
import com.sopt.dive.component.InfoItem
import com.sopt.dive.ui.theme.DiveTheme
import com.sopt.dive.util.IntentKeys

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        // 로그인 화면에서 받은 데이터
        val id = intent.getStringExtra(IntentKeys.ID) ?: ""
        val password = intent.getStringExtra(IntentKeys.PASSWORD) ?: ""
        val nickname = intent.getStringExtra(IntentKeys.NICKNAME) ?: ""
        val etc = intent.getStringExtra(IntentKeys.ETC) ?: ""

        setContent {
            DiveTheme {
                MyPageScreen(
                    id = id,
                    password = password,
                    nickname = nickname,
                    etc = etc
                )
            }
        }
    }
}

@Composable
private fun MyPageScreen(
    id: String,
    password: String,
    nickname: String,
    etc: String
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.safeDrawing)
            .padding(
                horizontal = 40.dp,
                vertical = 20.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 프로필 이미지
        Image(
            painter = painterResource(id = R.drawable.profile),
            contentDescription = "${nickname}의 프로필 이미지",
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(24.dp))

        // 닉네임
        Text(
            text = nickname,
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(30.dp))

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
        MyPageScreen(id = "dd", password = "dd", nickname = "수현", etc = "" )
    }
}