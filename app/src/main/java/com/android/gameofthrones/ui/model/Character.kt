package com.android.gameofthrones.ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Character(
    val id: Int,
    val family: String,
    val fullName: String,
    val imageUrl: String,
    val title: String
) : Parcelable
