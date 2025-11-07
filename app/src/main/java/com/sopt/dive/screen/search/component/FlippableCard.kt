package com.sopt.dive.screen.search.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource

@Composable
fun FlippableCard(
    modifier: Modifier = Modifier,
    rotationYDeg: Float,
    showBack: Boolean,
    frontResId: Int,
    backResId: Int
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        // 앞면
        Image(
            painter = painterResource(frontResId),
            contentDescription = "카드 앞면",
            modifier = Modifier
                .matchParentSize()
                .graphicsLayer {
                    val base = if (showBack) 180f else 0f
                    rotationY = base + rotationYDeg
                    alpha = if (showBack) 0f else 1f
                    cameraDistance = 12 * density
                }
        )

        // 뒷면
        Image(
            painter = painterResource(backResId),
            contentDescription = "카드 뒷면",
            modifier = Modifier
                .matchParentSize()
                .graphicsLayer {
                    val base = if (showBack) 0f else 180f
                    rotationY = base + rotationYDeg
                    alpha = if (showBack) 1f else 0f
                    cameraDistance = 12 * density
                }
        )
    }
}