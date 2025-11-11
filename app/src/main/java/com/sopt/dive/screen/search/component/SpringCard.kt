package com.sopt.dive.screen.search.component

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex

@Composable
fun SpringCard(
    modifier: Modifier = Modifier,
    frontResId: Int,
    backResId: Int,
) {
    var isFlipped by remember { mutableStateOf(false) }

    // 회전 애니메이션
    val rotationAngleY by animateFloatAsState(
        targetValue = if (isFlipped) 180f else 0f,
        animationSpec = spring(stiffness = 177.8f, dampingRatio = 0.75f),
        label = "rotation"
    )

    // 둥근 모서리 정의
    val frontShape = RoundedCornerShape(
        topStart = 8.dp,
        topEnd = 80.dp,
        bottomStart = 80.dp,
        bottomEnd = 8.dp
    )
    val backShape = RoundedCornerShape(
        topStart = 80.dp,
        topEnd = 8.dp,
        bottomStart = 8.dp,
        bottomEnd = 80.dp
    )

    val frontVisible = rotationAngleY <= 90f

    Box(
        modifier = modifier.clickable { isFlipped = !isFlipped },
        contentAlignment = Alignment.Center
    ) {
        if (frontVisible) {
            // 앞면이 위에 있을 때
            Image(
                painter = painterResource(backResId),
                contentDescription = "Card Back",
                modifier = Modifier
                    .matchParentSize()
                    .clip(backShape)
            )
            Image(
                painter = painterResource(frontResId),
                contentDescription = "Card Front",
                modifier = Modifier
                    .matchParentSize()
                    .graphicsLayer {
                        rotationY = rotationAngleY
                        cameraDistance = 12 * density
                        translationX = if (isFlipped) 20f else 0f
                        translationY = if (isFlipped) 20f else 0f
                    }
                    .zIndex(1f)
                    .clip(frontShape)
            )
        } else {
            // 뒷면이 위에 있을 때
            Image(
                painter = painterResource(frontResId),
                contentDescription = "Card Front",
                modifier = Modifier
                    .matchParentSize()
                    .graphicsLayer {
                        rotationY = rotationAngleY
                        cameraDistance = 12 * density
                        translationX = if (isFlipped) 20f else 0f
                        translationY = if (isFlipped) 20f else 0f
                    }
                    .zIndex(0f)
                    .clip(frontShape)
            )
            Image(
                painter = painterResource(backResId),
                contentDescription = "Card Back",
                modifier = Modifier
                    .matchParentSize()
                    .clip(backShape)
            )
        }
    }
}