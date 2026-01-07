package com.example.musicplayer.player

import android.content.Context
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import javax.inject.Singleton
import com.example.musicplayer.Data.API.Repository.Song

@Singleton
class MusicPlayerManager @Inject constructor(
    @ApplicationContext context: Context
) {

    private val exoPlayer = ExoPlayer.Builder(context).build()

    private val _playbackState = MutableStateFlow(PlaybackState())
    val playbackState: StateFlow<PlaybackState> = _playbackState

    private var progressJob: Job? = null

    fun playQueue(songs: List<Song>, startIndex: Int) {
        val mediaItems = songs.map {
            MediaItem.fromUri(it.audioUrl)
        }

        exoPlayer.setMediaItems(mediaItems, startIndex, 0L)
        exoPlayer.prepare()
        exoPlayer.play()

        _playbackState.value = PlaybackState(
            song = songs[startIndex],
            isPlaying = true,
            queue = songs,
            currentIndex = startIndex,
            duration = songs[startIndex].duration.toLong()
        )

        startProgressUpdates()
    }

    fun playNext() {
        val state = _playbackState.value
        val nextIndex = state.currentIndex + 1

        if (nextIndex < state.queue.size) {
            exoPlayer.seekTo(nextIndex, 0L)
            exoPlayer.play()

            val nextSong = state.queue[nextIndex]
            _playbackState.value = state.copy(
                song = nextSong,
                currentIndex = nextIndex,
                isPlaying = true,
                position = 0L,
                duration = nextSong.duration.toLong()
            )
        }
    }

    fun playPrevious() {
        val state = _playbackState.value
        val prevIndex = state.currentIndex - 1

        if (prevIndex >= 0) {
            exoPlayer.seekTo(prevIndex, 0L)
            exoPlayer.play()

            val prevSong = state.queue[prevIndex]
            _playbackState.value = state.copy(
                song = prevSong,
                currentIndex = prevIndex,
                isPlaying = true,
                position = 0L,
                duration = prevSong.duration.toLong()
            )
        }
    }

    fun togglePlayPause() {
        if (exoPlayer.isPlaying) exoPlayer.pause() else exoPlayer.play()

        _playbackState.value = _playbackState.value.copy(
            isPlaying = exoPlayer.isPlaying
        )
    }

    fun seekTo(position: Long) {
        exoPlayer.seekTo(position)
        _playbackState.value = _playbackState.value.copy(position = position)
    }

    private fun startProgressUpdates() {
        progressJob?.cancel()
        progressJob = CoroutineScope(Dispatchers.Main).launch {
            while (true) {
                _playbackState.value = _playbackState.value.copy(
                    position = exoPlayer.currentPosition
                )
                delay(500)
            }
        }
    }
}
