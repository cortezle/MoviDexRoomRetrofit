package com.example.moviedex.Fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.moviedex.Database.Entity.Movie

import com.example.moviedex.R
import com.example.moviedex.ViewModel.MovieViewModel
import kotlinx.android.synthetic.main.fragment_movie_detail.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [MovieDetailFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [MovieDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class MovieDetailFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: Int? = null
    private var listener: OnFragmentInteractionListener? = null

    private lateinit var movie: Movie
    private lateinit var viewModel: MovieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(MovieViewModel::class.java)

        movie = Movie()

        arguments?.let {
            param1 = it.getInt(ARG_PARAM1)
        }

    }

    override fun onStart() {
        super.onStart()

        viewModel.getAll().observe(this, Observer {
            movie = it[param1!!]
            Glide.with(this).load(movie?.Poster).into(detail_image)
            detail_title.text = movie.Title+"\n("+movie.Year+")"
            detail_released.text = "Release date: "+movie.Released
            detail_time.text = "Runtime: "+movie.Runtime
            detail_genre.text = "Genres: "+movie.Genre+"."
            detail_director.text = "Director: "+movie.Director
            detail_plot.text = movie.Plot
        })

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_detail, container, false)
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
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
         * @return A new instance of fragment MovieDetailFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MovieDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                }
            }
    }
}
