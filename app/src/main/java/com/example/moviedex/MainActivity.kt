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
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity :
        AppCompatActivity(),
        MovieListFragment.OnFragmentInteractionListener,
        MovieDetailFragment.OnFragmentInteractionListener,
        FragmentCommunication{

    override fun removeDetail() {
        if (detalle != null) {
            supportFragmentManager
                .beginTransaction()
                .remove(detalle!!)
                .commit()
            detalle = null
        }
    }

    private var detalle: MovieDetailFragment? = null

    override fun sendData(data: Int) {

        detalle = MovieDetailFragment()
        var datos = Bundle()
        datos.putInt("param1", data)
        detalle!!.arguments = datos

        if(detail_container != null){
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.detail_container, detalle!!)
                .addToBackStack("prev")
                .commit()
        }else {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, detalle!!)
                .addToBackStack("prev")
                .commit()
        }

    }

    override fun onFragmentInteraction(uri: Uri) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState == null || detail_container != null){
            var listado = MovieListFragment()
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container, listado).commit()
        }

    }
}
