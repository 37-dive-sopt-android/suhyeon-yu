package com.sopt.dive.component.item

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
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

@Composable
fun MusicBadge(text: String) {
    Box(
        modifier = Modifier
            .height(26.dp)
            .padding(top = 2.dp)
            .border(
                width = 1.dp,
                color = Color(0xFF1ED760),
                shape = RoundedCornerShape(50)
            )
            .padding(horizontal = 10.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodySmall,
            color = Color(0xFF1ED760)
        )
    }
}

@Composable
fun BirthdayBadge() {
    Box(
        modifier = Modifier
            .height(26.dp)
            .padding(top = 2.dp)
            .border(
                width = 1.dp,
                color = Color(0xFFFF0053),
                shape = RoundedCornerShape(50)
            )
            .padding(horizontal = 10.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "🎉️ HAPPY BIRTHDAY🎂",
            style = MaterialTheme.typography.bodySmall,
            color = Color(0xFFFF0053)
        )
    }
}



@Preview(showBackground = true)
@Composable
private fun MusicBadgePreview() {
    MusicBadge(text = "music")
}

@Preview(showBackground = true)
@Composable
private fun BirthdayBadgePreview() {
    BirthdayBadge()
}
