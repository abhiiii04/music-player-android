MUSIC PLAYER APP (ANDROID)

A modern Android music streaming application built using Jetpack Compose, MVVM architecture, and Media3 ExoPlayer, supporting seamless playback with synchronized UI, mini player, and full player experience.


Setup Instructions

Prerequisites

1.Android Studio

2.Android SDK API level 26+

3.Active internet connection (for streaming music)

Steps

1.Clone the repository: git clone https://github.com/abhiiii04/MusicPlayer.git

2.Open the project in Android Studio

3.Allow Gradle to sync completely

4.Run the app on: 
  Android Emulator (Pixel 6 / API 34 recommended), or 
  Physical Android device
  
5.No API keys or additional configuration required



Architecture Overview

The app follows MVVM (Model–View–ViewModel) architecture with Jetpack Compose and unidirectional data flow.

Layers

1.UI Layer (Compose)

○ Home Screen

○ Mini Player

○ Full Player

○ Reacts to state changes only

2.ViewModel Layer

○ Manages UI state using StateFlow

○ Handles user actions (play, pause, seek, navigation)

○ Acts as the single source of truth for UI

3.Data Layer

○ MusicRepository handles API calls using Retrofit

○ Maps API responses to domain models

4.Playback Layer

○ MusicPlayerManager wraps Media3 ExoPlayer

○ Manages playback, queue, seek, and progress updates

○ Exposes playback state via StateFlow

Key Technologies

1. Jetpack Compose

2. Kotlin Coroutines & StateFlow

3. Hilt (Dependency Injection)

4. Retrofit

5. Media3 ExoPlayer

6. Navigation Compose


Assumptions & Trade-offs

Assumptions

1.Users have a stable internet connection

2.Music is streamed only (no file downloads)

3.Only one playback session runs at a time

4.API endpoints remain available and stable

Trade-offs

1.Offline playback is not supported

2.Playback state is not persisted across app restarts

3.Queue reordering is implemented at logic level, not UI level

4.Media notification controls are optional and not mandatory

5.These trade-offs were made to keep the codebase clean, readable, and focused on core playback functionality.














