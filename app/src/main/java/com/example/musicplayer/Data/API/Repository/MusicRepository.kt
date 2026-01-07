package com.example.musicplayer.Data.API.Repository

import javax.inject.Inject

class MusicRepository @Inject constructor(
    private val api: SaavnApi
) {

    suspend fun getTopCharts(): List<Song> {
        val response = api.getTopCharts()

        return response.data.results.map { dto ->
            Song(
                id = dto.id,
                title = dto.name,

                artist = dto.artists.primary.firstOrNull()?.name ?: "Unknown Artist",

                imageUrl = dto.image.lastOrNull()?.url ?: "",

                audioUrl = dto.downloadUrl.find { it.quality == "320kbps" }?.url
                    ?: dto.downloadUrl.find { it.quality == "160kbps" }?.url
                    ?: dto.downloadUrl.firstOrNull()?.url
                    ?: "",
                duration = dto.duration
            )
        }
    }

    suspend fun searchSongs(query: String): List<Song> {
        val response = api.searchSongs(query)

        return response.data.results.map { dto ->
            Song(
                id = dto.id,
                title = dto.name,
                artist = dto.artists.primary.firstOrNull()?.name ?: "Unknown Artist",
                imageUrl = dto.image.lastOrNull()?.url ?: "",
                audioUrl = dto.downloadUrl.find { it.quality == "320kbps" }?.url
                    ?: dto.downloadUrl.find { it.quality == "160kbps" }?.url
                    ?: dto.downloadUrl.firstOrNull()?.url
                    ?: "",
                duration = dto.duration
            )
        }
    }
}
