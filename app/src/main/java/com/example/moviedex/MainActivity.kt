package com.example.moviedex

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.moviedex.Fragments.MovieDetailFragment
import com.example.moviedex.Fragments.MovieListFragment
import com.example.moviedex.Interfaces.FragmentCommunication
import com.example.moviedex.ViewModel.MovieViewModel

class MainActivity :
        AppCompatActivity(),
        MovieListFragment.OnFragmentInteractionListener,
        MovieDetailFragment.OnFragmentInteractionListener,
        FragmentCommunication{


    override fun sendData(data: Int) {

        var detalle = MovieDetailFragment()
        var datos = Bundle()
        datos.putInt("param1", data)
        detalle.arguments = datos
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, detalle)
                .addToBackStack("prev")
                .commit()

    }

    override fun onFragmentInteraction(uri: Uri) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState == null){
            var listado = MovieListFragment()
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container, listado).commit()
        }

    }
}
