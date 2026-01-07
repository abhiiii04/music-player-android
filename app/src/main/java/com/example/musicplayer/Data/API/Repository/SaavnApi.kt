package com.example.musicplayer.Data.API.Repository

import retrofit2.http.GET
import retrofit2.http.Query

interface SaavnApi {


    @GET("search/songs")
    suspend fun searchSongs(
        @Query("query") query: String,
        @Query("limit") limit: Int = 20
    ): ApiResponse


    @GET("songs")
    suspend fun getSongDetails(
        @Query("id") songId: String
    ): ApiResponse




    @GET("search/songs?query=Trending&limit=50")
    suspend fun getTopCharts(): ApiResponse
}
