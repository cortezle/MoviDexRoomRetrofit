package com.example.moviedex.ViewModel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.moviedex.Database.Entity.Movie
import com.example.moviedex.Database.MainDatabase
import com.example.moviedex.Database.service.MovieService
import com.example.moviedex.Repository.MovieRepository
import kotlinx.coroutines.launch

class MovieViewModel(private val app: Application) : AndroidViewModel(app)  {
    private val repository: MovieRepository

    init {

        val movieDao = MainDatabase.getDatabase(app).movieDao()
        val movieService = MovieService.getRetrofit()
        repository = MovieRepository(movieDao, movieService)
    }

    private suspend fun insert(movie: Movie) = repository.insert(movie)

    fun getAll(): LiveData<List<Movie>> {
        return repository.getAllfromRoomDB()
    }

    //Buscar movie por nombre

    private suspend fun SearchOneMovie(name: String) = repository.getMovieByName(name)

    //Eliminar movie
    private suspend fun delete() = repository.delete()

    //Obtener movie
    fun retrieveMovie(movie: String)=viewModelScope.launch{

        this@MovieViewModel.delete()

        val response = repository.retrieveMoviesByNameAsync(movie).await()
        if(response.isSuccessful) with(response){
            this.body()?.forEach{
                this@MovieViewModel.insert(it)
            }
        }else with(response){
            when(response.code()){
                404->{
                    Toast.makeText(app, "No se encontro la pelicula", Toast.LENGTH_LONG).show()
                }
            }

        }
    }
}