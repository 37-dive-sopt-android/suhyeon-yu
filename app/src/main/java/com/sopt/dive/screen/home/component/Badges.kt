package com.sopt.dive.screen.home.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

enum class BadgeType(val defaultText: String, val color: Color) {
    MUSIC("", Color(0xFF1ED760)),
    BIRTHDAY("🎉️ HBD🎂", Color(0xFFFF0053))
}

@Composable
fun Badge(
    type: BadgeType,
    text: String? = null,
    modifier: Modifier = Modifier
) {
    val displayText = when (type) {
        BadgeType.MUSIC -> text ?: "Music"
        BadgeType.BIRTHDAY -> type.defaultText
    }

    Box(
        modifier = modifier
            .height(26.dp)
            .padding(top = 2.dp)
            .border(
                width = 1.dp,
                color = type.color,
                shape = RoundedCornerShape(50)
            )
            .padding(horizontal = 10.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = displayText,
            style = MaterialTheme.typography.bodySmall,
            color = type.color
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun BadgePreview() {
    Column {
        Badge(type = BadgeType.MUSIC)
        Spacer(modifier = Modifier.height(8.dp))
        Badge(type = BadgeType.BIRTHDAY)
    }
}
