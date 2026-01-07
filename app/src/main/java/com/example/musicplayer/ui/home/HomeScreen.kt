package com.example.musicplayer.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.musicplayer.presentation.viewmodel.HomeViewModel
import com.example.musicplayer.player.MiniPlayer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onMiniPlayerClick: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    val playbackState by viewModel.playbackState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadTopCharts()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Music Player") }
            )
        },
        bottomBar = {
            if (playbackState.song != null) {
                MiniPlayer(
                    state = playbackState,
                    onPlayPauseClick = { viewModel.togglePlayPause() },
                    onExpandClick = onMiniPlayerClick
                )

            }
        }
    ) { padding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {

            when {
                uiState.isLoading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                uiState.error != null -> {
                    Text(
                        text = uiState.error ?: "Unknown error",
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                else -> {
                    LazyColumn {
                        items(uiState.songs) { song ->
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        viewModel.playSong(song)
                                    }
                                    .padding(16.dp)
                            ) {
                                Text(
                                    text = song.title,
                                    style = MaterialTheme.typography.titleMedium
                                )
                                Text(
                                    text = song.artist,
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }
                            Divider()
                        }
                    }
                }
            }
        }
    }
}
