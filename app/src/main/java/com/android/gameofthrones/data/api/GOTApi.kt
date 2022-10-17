package com.android.gameofthrones.data.api

import com.android.gameofthrones.data.model.GOTApiResponseSchema
import retrofit2.http.GET

interface GOTApi {

    @GET("Characters")
    suspend fun getCharacters(): GOTApiResponseSchema
}
