package com.example.flixter.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.flixter.DetailActivity;
import com.example.flixter.R;
import com.example.flixter.models.Movie;

import org.parceler.Parcels;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    Context context;
    List<Movie> movies;

    public MovieAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("MovieAdapter", "onCreateViewHolder");
        View movieView = LayoutInflater.from(context).inflate(R.layout.item_movie,parent,false);
        return new ViewHolder(movieView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // get the movie
        //bind movie data into view holder
        Log.d("MovieAdapter", "onBindViewHolder" + position);
        Movie movie  = movies.get(position);
        holder.bind(movie);


    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout container;
        TextView tvTitle;
        TextView tvOverview;
        ImageView ivPoster;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvOverview = itemView.findViewById(R.id.tvOverview);
            ivPoster = itemView.findViewById(R.id.idPoster);
            container = itemView.findViewById(R.id.container);
        }

            public void bind(final Movie movie) {
                tvTitle.setText(movie.getTitle());
                tvOverview.setText(movie.getOverview());
                String imageUrl;

                // if phone is in landscape
                // then image url == backdrop
                // else poster image

                if(context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    imageUrl = movie.getBackdropPath();
                } else {
                    imageUrl = movie.getPosterPath();
                }


                Glide.with(context).load(imageUrl).into(ivPoster);

                //Register click listener
                // navigate to new detail view
                container.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(context, DetailActivity.class);

                        i.putExtra("movie", Parcels.wrap(movie));
                        context.startActivity(i);
                    }
                });

            }
    }
}
