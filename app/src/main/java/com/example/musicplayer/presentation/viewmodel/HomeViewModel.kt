package com.example.musicplayer.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicplayer.Data.API.Repository.MusicRepository
import com.example.musicplayer.Data.API.Repository.Song
import com.example.musicplayer.player.MusicPlayerManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: MusicRepository,
    private val player: MusicPlayerManager
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState


    val playbackState = player.playbackState

    fun loadTopCharts() {
        viewModelScope.launch {
            _uiState.value = HomeUiState(isLoading = true)

            try {
                val songs = repository.getTopCharts()
                _uiState.value = HomeUiState(songs = songs)
            } catch (e: Exception) {
                _uiState.value = HomeUiState(
                    error = e.message ?: "Something went wrong"
                )
            }
        }
    }


    fun playSong(song: Song) {
        val songs = uiState.value.songs
        val index = songs.indexOf(song)

        if (index != -1) {
            player.playQueue(songs, index)
        }
    }


    fun togglePlayPause() {
        player.togglePlayPause()
    }


    fun playNext() {
        player.playNext()
    }


    fun playPrevious() {
        player.playPrevious()
    }


    fun seekTo(position: Long) {
        player.seekTo(position)
    }

    fun searchSongs(query: String) {
        viewModelScope.launch {
            _uiState.value = HomeUiState(isLoading = true)

            try {
                val songs = repository.searchSongs(query)
                _uiState.value = HomeUiState(songs = songs)
            } catch (e: Exception) {
                _uiState.value = HomeUiState(
                    error = e.message ?: "Search failed"
                )
            }
        }
    }
}
