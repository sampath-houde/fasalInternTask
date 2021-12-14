package com.example.fasakinternshiptask.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fasakinternshiptask.model.MovieRepo
import com.example.fasakinternshiptask.model.MovieSearchResponse
import com.example.fasakinternshiptask.utils.ApiResponseHandler
import kotlinx.coroutines.launch

class MovieViewModel(private val repository: MovieRepo): ViewModel() {

    private val _movie_list: MutableLiveData<ApiResponseHandler<MovieSearchResponse>> =
        MutableLiveData()

    val movieList: LiveData<ApiResponseHandler<MovieSearchResponse>>
        get() = _movie_list

    fun getMovie(keyword: String) = viewModelScope.launch {
        _movie_list.value = repository.getMovie(keyword)
    }
}