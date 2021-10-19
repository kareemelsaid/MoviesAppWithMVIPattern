package com.example.mvipattern.ui

import android.content.res.Resources
import androidx.constraintlayout.motion.utils.ViewState
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvipattern.R
import com.example.mvipattern.repos.HomeRepoInterface
import com.example.mvipattern.utilities.managers.ApiRequestManagerInterface
import com.example.mvipattern.utilities.managers.InternetConnectionManagerInterface
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@DelicateCoroutinesApi
class MoviesViewModel @Inject constructor(
    private val internetConnectionManager: InternetConnectionManagerInterface,
    private val apiRequestManager: ApiRequestManagerInterface,
    private val homeRepoInterface: HomeRepoInterface,
    private val resources: Resources,
) : ViewModel() {
    val moviesIntent = Channel<MoviesIntent>(Channel.UNLIMITED)
    private val _state = MutableStateFlow<MoviesState>(MoviesState.Idle)
    val state: StateFlow<MoviesState> get() = _state
    val totalPages = MutableLiveData<Int>()

    init {
        handleIntent()
    }

    @DelicateCoroutinesApi
    private fun handleIntent() {
        GlobalScope.launch {
            moviesIntent.consumeAsFlow().collect {
                when (it) {
                    is MoviesIntent.GetMovies -> getMovie(page = 1)
                }
            }
        }
    }


    private fun getMovie(page: Int = 1) {
        if (internetConnectionManager.isConnectedToInternet) {
            _state.value = MoviesState.Loading
            apiRequestManager.execute(
                request = {
                    homeRepoInterface.movie(page)
                },
                onSuccess = { data, headers, statusCode ->
                    totalPages.value = data.total_pages
                    if (data.results.isNotEmpty()) {
                        _state.value = MoviesState.Movies(data.results)
                    }
                },
                onFailure = { message, statusCode ->
                    _state.value = MoviesState.Error(message.errorMessage)
                }
            )

        } else {
            _state.value = MoviesState.Error(resources.getString(R.string.no_internet_connection))
        }
    }


}