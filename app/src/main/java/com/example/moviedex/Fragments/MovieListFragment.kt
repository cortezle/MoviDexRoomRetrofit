package com.example.moviedex.Fragments

import android.app.Activity
import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviedex.Adapter.MovieAdapter
import com.example.moviedex.Database.Entity.Movie
import com.example.moviedex.Interfaces.FragmentCommunication

import com.example.moviedex.R
import com.example.moviedex.ViewModel.MovieViewModel
import kotlinx.android.synthetic.main.fragment_movie_list.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [MovieListFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [MovieListFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class MovieListFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null

    private lateinit var activity: Activity
    private lateinit var comunicacion: FragmentCommunication

    private lateinit var moviesList: List<Movie>

    private lateinit var viewAdapter: MovieAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    private lateinit var viewModel: MovieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        viewModel = ViewModelProviders.of(this).get(MovieViewModel::class.java)

        moviesList = listOf(Movie())
        viewModel.getAll().observe(this, Observer {
            moviesList = it
            viewAdapter.setData(moviesList)
        })
    }

    override fun onStart() {
        super.onStart()
        viewAdapter = MovieAdapter(moviesList)
        viewManager = LinearLayoutManager(context)

        initRecycler()

        btn_search.setOnClickListener {
            if (et_query.text.toString().isNotBlank() && et_query.text.toString().isNotEmpty()) {
                viewModel.retrieveMovie(et_query.text.toString())
            }else{
                Toast.makeText(this.context, "Debe ingresar texto antes de buscar.", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun initRecycler() {
        with(rv_moviesList){
            adapter = viewAdapter
            layoutManager = viewManager
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_list, container, false)
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is Activity){
            activity = context
            if (activity is FragmentCommunication){
                comunicacion = activity as FragmentCommunication
            }
        }

        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MovieListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                MovieListFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}
