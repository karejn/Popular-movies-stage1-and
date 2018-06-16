package com.example.android.popularmovies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.Explode;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by ekzhu on 16.06.2018.
 */

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        getWindow().setEnterTransition(new Explode());

        Intent intent = getIntent();
        if (intent.hasExtra(getString(R.string.extra_movie))) {
            Movie movie = intent.getParcelableExtra(getString(R.string.extra_movie));
            populateUI(movie);
        }
    }

    private void populateUI(Movie movie) {

        ImageView mPosterImageView = findViewById(R.id.iv_poster);
        TextView mTitleTextView = findViewById(R.id.tv_title);
        TextView mYearTextView = findViewById(R.id.tv_year);
        RatingBar mRatingRatingBar = findViewById(R.id.rb_rating);
        TextView mVotesTextView = findViewById(R.id.tv_votes);
        TextView mDescriptionTextView = findViewById(R.id.tv_description);

        android.support.v7.app.ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(movie.getTitle());
        }

        String url = NetworkUtils.buildImageUrl(movie.getPosterPath());
        Picasso.get().load(url).placeholder(R.drawable.no_picture).into(mPosterImageView);

        mTitleTextView.setText(movie.getTitle());
        mYearTextView.setText(movie.getReleaseDate().substring(0, 4));
        mRatingRatingBar.setRating(movie.getVoteAverage() / 2f);
        mVotesTextView.setText(getResources().getString(R.string.number_of_votes, String.valueOf(movie.getVoteCount())));
        mDescriptionTextView.setText(movie.getDescription());

    }
}
