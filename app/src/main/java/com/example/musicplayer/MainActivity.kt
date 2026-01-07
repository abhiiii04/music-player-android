package com.example.musicplayer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint

import com.example.musicplayer.ui.home.HomeScreen
import com.example.musicplayer.player.FullPlayerScreen
import com.example.musicplayer.ui.navigation.Routes
import com.example.musicplayer.presentation.viewmodel.HomeViewModel
import com.example.musicplayer.ui.theme.MusicPlayerTheme
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            MusicPlayerTheme {

                val navController = rememberNavController()


                val homeViewModel: HomeViewModel = hiltViewModel()

                val playbackState =
                    homeViewModel.playbackState.collectAsState().value

                Surface(color = MaterialTheme.colorScheme.background) {

                    NavHost(
                        navController = navController,
                        startDestination = Routes.HOME
                    ) {

                        composable(Routes.HOME) {
                            HomeScreen(
                                viewModel = homeViewModel,
                                onMiniPlayerClick = {
                                    navController.navigate(Routes.PLAYER)
                                }
                            )
                        }

                        composable(Routes.PLAYER) {
                            FullPlayerScreen(
                                state = playbackState,
                                onPlayPauseClick = { homeViewModel.togglePlayPause() },
                                onSeek = { homeViewModel.seekTo(it) },
                                onNextClick = { homeViewModel.playNext() },
                                onPreviousClick = { homeViewModel.playPrevious() },
                                onBackClick = { navController.popBackStack() }
                            )

                        }

                        }
                    }
                }
            }
        }

}
