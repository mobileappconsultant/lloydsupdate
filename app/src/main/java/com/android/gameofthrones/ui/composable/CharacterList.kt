package com.android.gameofthrones.ui.composable

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import com.android.gameofthrones.ui.model.Character

@ExperimentalCoilApi
@Composable
fun CharacterList(weatherItems: List<Character>, onItemSelect: (Character) -> Unit) {

    LazyColumn {
        items(
            weatherItems
        ) { result ->
            CharacterListItem(result, onItemSelect)
            Spacer(Modifier.height(16.dp))
        }
    }
}
