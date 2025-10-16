package com.sopt.dive.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun InfoItem(
    title: String,
    value: String,
    modifier: Modifier = Modifier,
    titleSize: Int = 25,
    valueSize: Int = 20,
    spacing: Int = 8
) {
    Column(modifier = modifier) {
        Text(
            text = title,
            fontSize = titleSize.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(spacing.dp))

        Text(
            text = value,
            fontSize = valueSize.sp
        )
    }
}