package com.example.mvipattern.ui

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvipattern.R
import com.example.mvipattern.base.BaseActivity
import com.example.mvipattern.response.MoviesResponse
import com.example.mvipattern.utilities.extensions.toast.toast
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MoviesActivity : BaseActivity() {
    private lateinit var moviesRecyclerView: RecyclerView
    private lateinit var moviesViewModel: MoviesViewModel
    private var adapter = MoviesAdapter(arrayListOf())
    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)
        moviesRecyclerView = findViewById(R.id.moviesRecyclerView)
        setupViewModel()
        setupUI()
        observeViewModel()
        lifecycleScope.launch {
            moviesViewModel.moviesIntent.send(MoviesIntent.GetMovies)
        }
    }

    private fun setupViewModel() {
        moviesViewModel = ViewModelProviders.of(this, viewModelFactory)[MoviesViewModel::class.java]
    }

    private fun setupUI() {
        moviesRecyclerView.layoutManager = LinearLayoutManager(this)
        moviesRecyclerView.layoutManager = GridLayoutManager(applicationContext,3)
        moviesRecyclerView.adapter = adapter
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            moviesViewModel.state.collect {
                when (it) {
                    is MoviesState.Idle -> {
                    }
                    is MoviesState.Loading -> {
                        loadingDialog.show()
                    }
                    is MoviesState.Movies -> {
                        loadingDialog.dismiss()
                        renderMoviesList(it.movies)
                    }
                    is MoviesState.Error -> {
                        loadingDialog.dismiss()
                        toast(it.error.toString())
                    }
                }
            }
        }
    }

    private fun renderMoviesList(moviesResult: List<MoviesResponse.Result>) {
        moviesResult.let { listOfMovies ->
            listOfMovies.let {
                adapter.addData(it)
            }
        }
        adapter.notifyDataSetChanged()
    }
}