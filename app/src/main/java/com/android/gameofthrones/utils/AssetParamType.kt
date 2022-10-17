package com.android.gameofthrones.utils

import android.os.Bundle
import androidx.navigation.NavType
import com.android.gameofthrones.ui.model.Character
import com.google.gson.Gson

class AssetParamType : NavType<Character>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): Character? {
        return bundle.getParcelable(key)
    }

    override fun parseValue(value: String): Character {
        return Gson().fromJson(value, Character::class.java)
    }

    override fun put(bundle: Bundle, key: String, value: Character) {
        bundle.putParcelable(key, value)
    }
}
