package com.example.mvipattern.ui

import com.example.mvipattern.response.MoviesResponse

sealed class MoviesState {
    object Idle : MoviesState()
    object Loading : MoviesState()
    data class Movies(val movies: List<MoviesResponse.Result>) : MoviesState()
    data class Error(val error: String?) : MoviesState()
}
