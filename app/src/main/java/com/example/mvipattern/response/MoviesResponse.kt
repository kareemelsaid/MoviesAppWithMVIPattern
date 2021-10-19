package com.example.mvipattern.response

data class MoviesResponse(
    val page: Int,
    var results: List<Result> = listOf(),
    val total_pages: Int,
    val total_results: Int
) {
    data class Result(
        val id: Int? = null,
        val original_title: String? = null,
        val overview: String? = null,
        val poster_path: String? = null,
        val release_date: String? = null,
        val title: String? = null,
        val vote_average: Double? = null,
        val vote_count: Int? = null
    )
}