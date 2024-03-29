package com.example.moviedex.Database.service

import com.example.moviedex.Database.Entity.Movie
import com.example.moviedex.Database.Entity.OmbdMovieResponse
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

const val MOVIE_API_BASE_URI = "http://www.omdbapi.com/"

interface MovieService {

    @GET("/?apikey=b1974190")
    fun getMovies(@Query("s") Title: String):Deferred<Response<OmbdMovieResponse>>

    @GET("/?apikey=b1974190")
    fun getMovieData(@Query("i") id: String):Deferred<Response<Movie>>


    companion object{
        fun getRetrofit():MovieService = Retrofit.Builder()
            .baseUrl(MOVIE_API_BASE_URI)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
            .create(MovieService::class.java)
    }
}