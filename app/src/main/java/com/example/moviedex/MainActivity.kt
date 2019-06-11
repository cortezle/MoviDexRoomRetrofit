package com.example.moviedex

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.moviedex.Fragments.MovieListFragment
import com.example.moviedex.Interfaces.FragmentCommunication

class MainActivity : AppCompatActivity(), MovieListFragment.OnFragmentInteractionListener, FragmentCommunication {

    override fun sendData(data: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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
