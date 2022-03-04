package com.example.flixster

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import jp.wasabeef.glide.transformations.RoundedCornersTransformation

const val MOVIE_EXTRA = "Movie_Extra"
private const val TAG = "MovieAdapter"

class MovieAdapter(private val context: Context, private val movies: List<Movie>):
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.i(TAG, "onCreateViewHolder")
        val view= LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.i(TAG, "onBindViewHolder position $position")
        val movie = movies[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener{
        private val ivPoster = itemView.findViewById<ImageView>(R.id.ivPoster)
        private val tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)
        private val tvTOverView= itemView.findViewById<TextView>(R.id.tvOverview)

        init{
            itemView.setOnClickListener(this)
        }

        fun bind(movie: Movie){
            val radius = 40
            val margin = 0
            tvTitle.text = movie.title
            tvTOverView.text = movie.overview
            Glide.with(context)
                .load(movie.posterImageUrl)
                .centerCrop()
                .transform(RoundedCornersTransformation(radius, margin))
                .into(ivPoster)
        }

        override fun onClick(v:View) {
            //1. Get notified of the particular movie which was clicked
            val movie = movies[adapterPosition]
            //2. Use the intent system to navigate to the new activity
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(MOVIE_EXTRA, movie)
            context.startActivity(intent)
        }
    }

}
