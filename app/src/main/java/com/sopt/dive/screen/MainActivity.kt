package com.sopt.dive.screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.dive.ui.theme.DiveTheme
import com.sopt.dive.R
import com.sopt.dive.component.InfoItem

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 로그인 화면에서 받은 데이터
        val id = intent.getStringExtra("아이디") ?: ""
        val password = intent.getStringExtra("비밀번호") ?: ""
        val nickname = intent.getStringExtra("닉네임") ?: ""
        val etc = intent.getStringExtra("주량") ?: ""

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
fun MyPageScreen( // 읽기 전용 -> 인자로 전달 받기
        id: String,
        password: String,
        nickname: String,
        etc: String
) {
    Column (
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 40.dp, vertical = 50.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // 프로필 이미지
            Image(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = "profile",
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

            Column (
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.Start
            ) {
                InfoItem(title = "아이디", value = id)
                Spacer(modifier = Modifier.height(20.dp))

                InfoItem(title = "비밀번호", value = password)
                Spacer(modifier = Modifier.height(20.dp))

                InfoItem(title = "주량", value = etc)
                Spacer(modifier = Modifier.weight(1f))
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MyPageScreenPreview() {
    DiveTheme {
        MyPageScreen(id = "dd", password = "dd", nickname = "수현", etc = "" )
    }
}