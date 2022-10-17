package com.android.gameofthrones.data.source.remote

import com.android.gameofthrones.data.model.GOTApiResponseSchema

interface RemoteDataSource {
    suspend fun getAllCharacters(): GOTApiResponseSchema
}
