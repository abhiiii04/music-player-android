package com.example.musicplayer.Data.API.Repository

import com.example.musicplayer.Data.API.Repository.SaavnApi
import javax.inject.Inject

class SongRepository @Inject constructor(
    private val api: SaavnApi
) {

    suspend fun searchSongs(query: String) =
        api.searchSongs(query)

    suspend fun getSongDetails(songId: String) =
        api.getSongDetails(songId)

    suspend fun getTopCharts() =
        api.getTopCharts()
}
