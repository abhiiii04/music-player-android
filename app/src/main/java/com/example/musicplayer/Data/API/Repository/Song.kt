package com.example.musicplayer.Data.API.Repository

data class Song(
    val id: String,
    val title: String,
    val artist: String,
    val imageUrl: String,
    val audioUrl: String,
    val duration: Int
)
