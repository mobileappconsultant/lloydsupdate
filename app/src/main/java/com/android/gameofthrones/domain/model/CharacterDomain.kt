package com.android.gameofthrones.domain.model


data class CharacterDomain(
    val id: Int,
    val family: String,
    val fullName: String,
    val imageUrl: String,
    val title: String
)