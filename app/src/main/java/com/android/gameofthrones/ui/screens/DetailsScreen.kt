package com.android.gameofthrones.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.android.gameofthrones.ui.model.Character
import com.android.gameofthrones.ui.theme.DarkBlue
import com.android.gameofthrones.utils.Constants.DETAILS_TEST_TAG

@OptIn(ExperimentalCoilApi::class)
@Composable
fun DetailsScreen(character: Character, onBack: () -> Unit) {
    Box(
        modifier = Modifier
            .background(DarkBlue)
            .testTag(DETAILS_TEST_TAG)
    ) {
        Column(Modifier.fillMaxSize()) {
            IconButton(onClick = onBack) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "",
                    tint = Color.White
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Image(
                painter = rememberImagePainter(data = character.imageUrl),
                contentDescription = "Image Character",
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(16.dp))
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = character.fullName, fontSize = 20.sp, fontWeight = FontWeight.Medium, color = Color.White)
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "Title: ${character.title}", fontSize = 16.sp, fontWeight = FontWeight.Normal, color = Color.White)

                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "Family: ${character.family}", fontSize = 16.sp, fontWeight = FontWeight.Normal, color = Color.White)

                Spacer(modifier = Modifier.height(16.dp))

            }
        }
    }
}
