package com.example.android.popularmovies;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by ekzhu on 16.06.2018.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private ArrayList<Movie> mMovieData;

    private final MovieAdapterOnClickHandler mClickHandler;

    MovieAdapter(MovieAdapterOnClickHandler clickHandler, ArrayList<Movie> movies) {
        this.mClickHandler = clickHandler;
        this.mMovieData = movies;
    }

    public interface MovieAdapterOnClickHandler {
        void onClick(View v, String clickedMovie, int layoutPosition);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {

        String movieImagePath = mMovieData.get(position).getPosterPath();
        String movieTitle = mMovieData.get(position).getTitle();

        holder.mMovieTitle.setText(movieTitle);

        String url = NetworkUtils.buildImageUrl(movieImagePath);
        Picasso.get().load(url).placeholder(R.drawable.no_picture).into(holder.mMovieImageView);
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.movie_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(layoutIdForListItem, parent, false);
        return new MovieViewHolder(view);
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        final ImageView mMovieImageView;
        final TextView mMovieTitle;

        MovieViewHolder(View itemView) {
            super(itemView);
            mMovieImageView = itemView.findViewById(R.id.iv_movie_poster);
            mMovieTitle = itemView.findViewById(R.id.tv_movie_title);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            String movieTitle = mMovieData.get(adapterPosition).getOriginalTitle();
            mClickHandler.onClick(v, movieTitle, getAdapterPosition());

        }
    }

    @Override
    public int getItemCount() {
        if (mMovieData == null) return 0;
        return mMovieData.size();
    }

    public void setMovieData(ArrayList<Movie> movieData) {
        mMovieData = movieData;
        notifyDataSetChanged();
    }


}
