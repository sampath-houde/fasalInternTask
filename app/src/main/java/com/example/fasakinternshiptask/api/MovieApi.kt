package com.example.fasakinternshiptask.api

import com.example.fasakinternshiptask.model.MovieSearchResponse
import com.task.solutiondeveloper.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

        @GET("?apikey=${Constants.API_KEY}")
        suspend fun getMovie(@Query("s") keyword: String) : MovieSearchResponse
}