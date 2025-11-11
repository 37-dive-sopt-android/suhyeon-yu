package com.sopt.dive.screen.search

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.dive.R
import com.sopt.dive.component.button.BasicButton
import com.sopt.dive.screen.search.component.FlippableCard
import com.sopt.dive.screen.search.component.SpringCard
import com.sopt.dive.ui.theme.DiveTheme

@Composable
fun SearchScreen() {
    var flipped by remember { mutableStateOf(false) }

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

       Row(
           modifier = Modifier.fillMaxWidth(),
           horizontalArrangement = Arrangement.SpaceEvenly,
           verticalAlignment = Alignment.CenterVertically
       ) {
           FlippableCard(
               modifier = Modifier
                   .fillMaxWidth(0.4f)
                   .aspectRatio(260f / 380f),
               rotationYDeg = rotationYDeg,
               showBack = showBack,
               frontResId = R.drawable.img_card_front,
               backResId = R.drawable.img_card_back
           )
           SpringCard(
               modifier = Modifier
                   .fillMaxWidth(0.4f)
                   .aspectRatio(260f / 380f)
           )
       }

        Spacer(modifier = Modifier.weight(1f))

        BasicButton(
            text = if (flipped) "다시 뒤집기" else "뒤집기",
            onClick = {
                flipped = !flipped
                singleRotation += 180f
            },
            modifier = Modifier
                .padding(bottom = 40.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SearchScreenPreview() {
    DiveTheme { SearchScreen() }
}