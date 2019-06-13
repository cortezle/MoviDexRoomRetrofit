package com.example.moviedex.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviedex.Database.Entity.Movie
import com.example.moviedex.Interfaces.RVClickListener
import com.example.moviedex.R
import kotlinx.android.synthetic.main.list_item.view.*

class MovieAdapter(var items: List<Movie>, var listener: View.OnClickListener): RecyclerView.Adapter<MovieAdapter.MovieHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)

        var holder = MovieHolder(view)
        holder.onClick(listener)

        return holder
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        holder.bind(items.get(position))
    }

    class MovieHolder(itemView: View): RecyclerView.ViewHolder(itemView), RVClickListener{

        override fun onClick(listener: View.OnClickListener){
            itemView.setOnClickListener(listener)
        }

        fun bind(item: Movie) = with(itemView){
            rv_tv_movieName.text = item.Title+" \n("+item.Year+")"
            if(list_image != null) {
                Glide.with(this).load(item.Poster).into(list_image)
            }
        }

    }

    fun setData(newList: List<Movie>){
        items = newList
        notifyDataSetChanged()
    }

}
