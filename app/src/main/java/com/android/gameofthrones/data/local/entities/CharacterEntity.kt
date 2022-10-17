package com.android.gameofthrones.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "character_table")
data class CharacterEntity(
    @PrimaryKey
    val id: Int,
    val family: String,
    val fullName: String,
    val imageUrl: String,
    val title: String
)
