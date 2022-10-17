package com.android.gameofthrones.data.source.remote

import com.android.gameofthrones.data.api.GOTApi
import com.android.gameofthrones.data.model.GOTApiResponseSchema
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(private val api: GOTApi) : RemoteDataSource {
    override suspend fun getAllCharacters(): GOTApiResponseSchema = api.getCharacters()
}
