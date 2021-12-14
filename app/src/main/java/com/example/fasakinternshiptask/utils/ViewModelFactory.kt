package com.task.newsapp.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.fasakinternshiptask.main.MovieViewModel
import com.example.fasakinternshiptask.model.MovieRepo
import com.example.fasakinternshiptask.utils.BaseRepository
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(
    private val repository: BaseRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MovieViewModel::class.java) -> MovieViewModel(repository as MovieRepo) as T
            else -> throw IllegalArgumentException("ViewModel class not found")
        }
    }

}