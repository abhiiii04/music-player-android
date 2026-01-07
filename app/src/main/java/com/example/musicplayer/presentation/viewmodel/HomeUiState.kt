
package com.example.musicplayer.presentation.viewmodel

import com.example.musicplayer.Data.API.Repository.Song

data class HomeUiState(
    val isLoading: Boolean = false,
    val songs: List<Song> = emptyList(),
    val error: String? = null
)
