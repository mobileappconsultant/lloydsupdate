package com.android.gameofthrones.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import com.android.gameofthrones.ui.model.Character
import com.android.gameofthrones.ui.theme.Gray
import com.android.gameofthrones.utils.Constants.CARD_ITEM_TEXT_TAG

@ExperimentalCoilApi
@Composable
fun CharacterListItem(character: Character, onItemSelect: (Character) -> Unit) {
    Card(
        modifier = Modifier
            .testTag(CARD_ITEM_TEXT_TAG)
            .fillMaxWidth()
            .background(Gray.copy(alpha = 0.7f), RoundedCornerShape(16.dp))
            .wrapContentHeight()
            .clickable {
                onItemSelect(character)
            },
        elevation = 0.dp,
        backgroundColor = Color.Transparent,
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(horizontalArrangement = Arrangement.Start, verticalAlignment = Alignment.CenterVertically) {
            CharacterImage(url = character.imageUrl)
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = character.fullName, fontSize = 20.sp, fontWeight = FontWeight.Medium, color = Color.White)
            Spacer(modifier = Modifier.width(16.dp))
        }
    }
}
