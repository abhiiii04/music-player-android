package com.example.musicplayer.Data.API.Repository

import com.google.gson.annotations.SerializedName


data class ApiResponse(
    val data: DataObj
)

data class DataObj(
    val results: List<SongDto>
)


data class SongDto(
    val id: String,
    val name: String,
    val language: String,
    val album: Album,
    val artists: Artists,
    val image: List<Image>,
    val downloadUrl: List<DownloadUrl>,
    val duration: Int

)

data class ArtistsDto(
    val primary: List<ArtistDto>
)

data class ArtistDto(
    val name: String
)

data class ImageDto(
    @SerializedName("link") val url: String,
    val quality: String
)

data class DownloadUrlDto(
    @SerializedName("link") val url: String,
    val quality: String
)
