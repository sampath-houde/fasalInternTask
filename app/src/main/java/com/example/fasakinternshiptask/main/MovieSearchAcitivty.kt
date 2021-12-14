package com.example.fasakinternshiptask.main

import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.fasakinternshiptask.R
import com.example.fasakinternshiptask.api.MovieApi
import com.example.fasakinternshiptask.api.RetrofitInstance
import com.example.fasakinternshiptask.databinding.ActivityMovieSearchAcitivtyBinding
import com.example.fasakinternshiptask.model.MovieRepo
import com.example.fasakinternshiptask.utils.ApiResponseHandler
import com.task.newsapp.utils.ViewModelFactory
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MovieSearchAcitivty : AppCompatActivity() {
    private lateinit var viewModel: MovieViewModel
    private lateinit var binding: ActivityMovieSearchAcitivtyBinding
    private var searchJob: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieSearchAcitivtyBinding.inflate(layoutInflater)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        window?.statusBarColor = ContextCompat.getColor(applicationContext, R.color.white)


        val api = RetrofitInstance.buildApi(MovieApi::class.java)
        val repo = MovieRepo(api)
        val factory = ViewModelFactory(repo)
        viewModel = ViewModelProvider(this, factory).get(MovieViewModel::class.java)
        setContentView(binding.root)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onStart() {
        super.onStart()

        binding.searchEditText.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                callApi()
            }

            override fun afterTextChanged(s: Editable?) {}

        })
    }


    private fun callApi() {
        val keyword = binding.searchEditText.text.toString().trim()

        binding.banner.visibility = View.GONE
        binding.recyclerView.visibility = View.GONE

        binding.progress.visibility = View.VISIBLE
        searchJob?.cancel()
        searchJob = lifecycleScope.launch {
            delay(350)
            viewModel.getMovie(keyword)
            viewModel.movieList.observe(this@MovieSearchAcitivty, {response->
                binding.progress.visibility = View.GONE
                when(response) {
                    is ApiResponseHandler.Success -> {
                        binding.recyclerView.visibility = View.VISIBLE
                        if(response.value.Search != null) {
                            val adapter = MovieAdapter(response.value.Search, this@MovieSearchAcitivty)
                            binding.recyclerView.adapter = adapter
                        }


                    }

                    is ApiResponseHandler.Failure -> {
                        binding.recyclerView.visibility = View.GONE
                        binding.banner.visibility = View.VISIBLE
                        if(response.isNetworkError) Toast.makeText(this@MovieSearchAcitivty, "Connection Error", Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }
        }
}