
package com.example.musicplayer.Data.API.Repository

import retrofit2.http.GET
import retrofit2.http.Query

interface SaavnApi {

    @GET("search/songs")
    suspend fun searchSongs(
        @Query("query") query: String,
        @Query("limit") limit: Int = 20,
        @Query("page") page: Int = 0
    ): Any // temporary, will replace with model
}
