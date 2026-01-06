package com.example.musicplayer.Data.API.Repository

import com.example.musicplayer.Data.API.Repository.SaavnApi
import javax.inject.Inject

class MusicRepository @Inject constructor(
    private val api: SaavnApi
) {
    suspend fun searchSongs(query: String) {
        api.searchSongs(query)
    }
}
