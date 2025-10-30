package com.sopt.dive.screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.remember
import com.sopt.dive.model.UserInfo
import com.sopt.dive.navigation.DiveApp
import com.sopt.dive.ui.theme.DiveTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            DiveTheme {
                val userInfo = remember { UserInfo() }
                DiveApp(userInfo = userInfo)
            }
        }
    }
}