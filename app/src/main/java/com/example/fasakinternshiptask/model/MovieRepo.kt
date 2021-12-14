package com.example.fasakinternshiptask.model

import com.example.fasakinternshiptask.api.MovieApi
import com.example.fasakinternshiptask.utils.BaseRepository

class MovieRepo(
    private val api: MovieApi
) : BaseRepository(){

    suspend fun getMovie(keyword: String) = safeApiCall {
        api.getMovie(keyword)
    }
}