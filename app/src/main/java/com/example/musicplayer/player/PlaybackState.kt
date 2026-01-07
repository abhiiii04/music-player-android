package com.example.musicplayer.player

import com.example.musicplayer.Data.API.Repository.Song

data class PlaybackState(
    val song: Song? = null,
    val isPlaying: Boolean = false,
    val position: Long = 0L,
    val duration: Long = 0L,
    val queue: List<Song> = emptyList(),
    val currentIndex: Int = -1
)
