package com.sopt.dive.component.text

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Title(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleLarge,
        color = Color.Black,
        textAlign = TextAlign.Center,
        fontSize = 30.sp,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun SectionTitle(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleMedium,
        modifier = Modifier.padding(vertical = 8.dp, horizontal = 4.dp)
    )
}