package com.sopt.dive.screen.search

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.dive.R
import com.sopt.dive.screen.search.component.FlipCard
import com.sopt.dive.screen.search.component.SpringCard
import com.sopt.dive.theme.DiveTheme

@Composable
fun SearchScreen() {
    var singleRotation by remember { mutableFloatStateOf(0f) }
    val singleRotationAnim by animateFloatAsState(
        targetValue = singleRotation,
        animationSpec = tween(durationMillis = 1000, easing = LinearEasing),
        label = ""
    )

    val rotationYDeg = singleRotationAnim % 360f

    val showBack = rotationYDeg > 90f && rotationYDeg < 270f
    // 회전 각이 90-270 -> 뒷면

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        FlipCard(
            modifier = Modifier
                .fillMaxWidth(0.6f)
                .aspectRatio(260f / 380f),
            rotationYDeg = rotationYDeg,
            showBack = showBack,
            frontResId = R.drawable.img_card_front,
            backResId = R.drawable.img_card_back,
            onClick = {
                singleRotation += 180f
            }
        )

        Spacer(modifier = Modifier.padding(5.dp))

        SpringCard(
            modifier = Modifier
                .fillMaxWidth(0.6f)
                .aspectRatio(200f / 200)
            ,
            frontResId = R.drawable.img_card_front,
            backText = "안녕하세요솝트짱".repeat(100)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SearchScreenPreview() {
    DiveTheme { SearchScreen() }
}