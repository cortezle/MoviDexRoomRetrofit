package com.example.moviedex.Repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.moviedex.Database.Dao.MovieDao
import com.example.moviedex.Database.Entity.Movie
import com.example.moviedex.Database.service.MovieService
import kotlinx.coroutines.Deferred
import retrofit2.Response

class MovieRepository(private val movieDao : MovieDao , private val api: MovieService) {


    fun retrieveMoviesByNameAsync(name:String): Deferred<Response<List<Movie>>> = api.getMovies(name)

    @WorkerThread
    suspend fun insert(movie: Movie) = movieDao.insertMovie(movie)

    fun getAllfromRoomDB(): LiveData<List<Movie>> = movieDao.loadAllMovies()

    fun getMovieByName(name: String) = movieDao.searchMovieByName(name)
}

