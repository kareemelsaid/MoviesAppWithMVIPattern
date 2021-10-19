package com.example.mvipattern.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mvipattern.R
import com.example.mvipattern.response.MoviesResponse
import com.example.mvipattern.utilities.Constants

class MoviesAdapter(private val moviesData: ArrayList<MoviesResponse.Result>) : RecyclerView.Adapter<MoviesAdapter.DataViewHolder>()
{
    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        fun bind(moviesData: MoviesResponse.Result)
        {
            Glide.with(itemView.context)
                .load(Constants.imageURL+moviesData.poster_path)
                .into(itemView.findViewById<View>(R.id.posterPathImageView) as ImageView)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            = DataViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.item_movie, parent,false))

    override fun getItemCount(): Int = moviesData.size
    override fun onBindViewHolder(holder: DataViewHolder,
                                  position: Int) =
        holder.bind(moviesData[position])
    fun addData(list: List<MoviesResponse.Result>)
    {
        moviesData.addAll(list)
    }
}