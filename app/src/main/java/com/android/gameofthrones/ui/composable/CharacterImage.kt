package com.android.gameofthrones.ui.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter

@OptIn(ExperimentalCoilApi::class)
@Composable
fun CharacterImage(url: String) {
    Image(
        modifier = Modifier
            .size(64.dp)
            .clip(RoundedCornerShape(8.dp)),
        painter = rememberImagePainter(url),
        contentDescription = "Weather Image"
    )
}
