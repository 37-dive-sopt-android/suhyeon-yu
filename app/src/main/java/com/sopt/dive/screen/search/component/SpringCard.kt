package com.sopt.dive.screen.search.component

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.sopt.dive.util.noRippleClickable

private enum class FlipState { Front, Back }

@Composable
fun SpringCard(
    modifier: Modifier = Modifier,
    frontResId: Int,
    backText: String,
) {
    var flipState by remember { mutableStateOf(FlipState.Front) }

    val transition = updateTransition(targetState = flipState, label = "flip-transition")

    val springSpec = spring<Float>(stiffness = 177.8f, dampingRatio = 0.75f)

    // 회전 애니메이션
    val rotationAngleY by transition.animateFloat(
        transitionSpec = { springSpec },
        label = "rotationY"
    ) { state ->
        if (state == FlipState.Back) 180f else 0f
    }

    // 뒷면 텍스트 투명도
    val textAlpha by transition.animateFloat(
        transitionSpec = { springSpec },
        label = "textAlpha"
    ) { state ->
        if (state == FlipState.Back) 1f else 0f
    }

    val frontShape = RoundedCornerShape(
        topStart = 20.dp,
        topEnd = 100.dp,
        bottomStart = 100.dp,
        bottomEnd = 20.dp
    )
    val backShape = RoundedCornerShape(
        topStart = 100.dp,
        topEnd = 20.dp,
        bottomStart = 20.dp,
        bottomEnd = 100.dp
    )

    Box(
        modifier = modifier.noRippleClickable {
            flipState = if (flipState == FlipState.Front) FlipState.Back else FlipState.Front
        },
        contentAlignment = Alignment.Center
    ) {
        // 뒷면 -> 텍스트
        Box(
            modifier = Modifier
                .fillMaxSize()
                .shadow(
                    elevation = if (rotationAngleY > 20f) 20.dp else 0.dp,
                    shape = backShape,
                    ambientColor = Color.Black.copy(alpha = 0.8f),
                    spotColor = Color.Black.copy(alpha = 0.8f),
                )
                .clip(backShape)
                .background(Color(0xFFA8E39A))
                .graphicsLayer {
                    clip = true
                    alpha = textAlpha
                },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = backText,
                fontSize = 10.sp,
                color = Color.Black,
                textAlign = TextAlign.Center,
                lineHeight = 15.sp,
            )
        }

        // 앞면 -> 이미지
        Image(
            painter = painterResource(frontResId),
            contentDescription = "Card Front",
            modifier = Modifier
                .matchParentSize()
                .offset {
                    val offsetPx = if (flipState == FlipState.Back) 20.dp.roundToPx() else 0
                    IntOffset(offsetPx, offsetPx)
                }
                // 그림자
                .shadow(
                    elevation = if (rotationAngleY <= 20f) 20.dp else 0.dp,
                    shape = frontShape,
                    ambientColor = Color.Black.copy(alpha = 0.8f),
                    spotColor = Color.Black.copy(alpha = 0.8f),
                )
                .graphicsLayer {
                    rotationY = rotationAngleY
                    cameraDistance = 12 * density
                    shape = frontShape // clip 대신 여기에 shape 설정
                    clip = true // 그래픽 레벨에서 잘라내기

                    //translationX = if (flipState == FlipState.Back) 20f else 0f
                    //translationY = if (flipState == FlipState.Back) 20f else 0f
                    // -> 이걸로 하면 이만큼 위가 잘리는 현상 발생 -> offset 사용해야 함
                }
                .zIndex(if (rotationAngleY <= 90f) 1f else -1f)
        )
    }
}

// shadow -> 그림자 외곽 가장 먼저 적용
// clip -> 카드 모양 자르기
// background -> 배경
// graphicsLayer -> 회전, 투명도 등 애니메이션 처리

// 회전할 때 안에 있는 사진만 회전하고 모양은 회전하지 않는 문제
// -> compose는 graphicsLayer -> clip 순서로 처리
// 즉 이미지 회전은 graphicsLayer에서 발생 -> 모양 자르기는 그 다음
// == clip을 graphphicsLayer 안쪽으로 옮기면 해결 가능