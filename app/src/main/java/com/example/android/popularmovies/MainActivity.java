package com.example.android.popularmovies;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by ekzhu on 16.06.2018.
 */

public class MainActivity extends AppCompatActivity implements MovieAdapter.MovieAdapterOnClickHandler {

    private URL url = null;


    private MovieAdapter mMovieAdapter;
    private RecyclerView mMoviesRecyclerView;
    private ProgressBar mLoadingIndicator;
    private TextView mErrorMessage;
    private ArrayList<Movie> movies = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMoviesRecyclerView = findViewById(R.id.rv_movies);
        mLoadingIndicator = findViewById(R.id.pb_loading_indicator);
        mErrorMessage = findViewById(R.id.tv_error_message);

        // Setting the span count for the GridLayoutManager based on the device's orientation
        int value = this.getResources().getConfiguration().orientation;
        GridLayoutManager layoutManager;
        if (value == Configuration.ORIENTATION_PORTRAIT) {
            layoutManager = new GridLayoutManager(this, 2);
        } else {
            layoutManager = new GridLayoutManager(this, 4);
        }

        mMoviesRecyclerView.setLayoutManager(layoutManager);
        mMoviesRecyclerView.setHasFixedSize(true);
        mMovieAdapter = new MovieAdapter(this, movies);
        mMoviesRecyclerView.setAdapter(mMovieAdapter);

        if (savedInstanceState == null || !savedInstanceState.containsKey(getString(R.string.saved_instance_movies))) {
            loadMovieData(R.id.menu_most_popular);
        } else {
            movies = savedInstanceState.getParcelableArrayList(getString(R.string.saved_instance_movies));
            if (movies != null) {
                mMovieAdapter.setMovieData(movies);
            }
        }
    }

    private void showErrorMessage() {
        mMoviesRecyclerView.setVisibility(View.INVISIBLE);
        mErrorMessage.setVisibility(View.VISIBLE);
    }

    private void showMovieData() {
        mMoviesRecyclerView.setVisibility(View.VISIBLE);
        mErrorMessage.setVisibility(View.INVISIBLE);
    }

    private void loadMovieData(int sortParam) {
        showMovieData();
        url = NetworkUtils.buildURL(sortParam);
        new FetchMoviesTask(this).execute(url);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList(getString(R.string.saved_instance_movies), movies);
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemThatWasClickedId = item.getItemId();
        url = null;

        switch (itemThatWasClickedId) {
            case R.id.menu_most_popular:
                loadMovieData(R.id.menu_most_popular);
                return true;
            case R.id.menu_top_rated:
                loadMovieData(R.id.menu_top_rated);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v, String clickedMovie, int layoutPosition) {
        Context context = this;
        Class destinationClass = DetailActivity.class;
        Intent startDetailActivityIntent = new Intent(context, destinationClass);
        startDetailActivityIntent.putExtra(getString(R.string.extra_movie), movies.get(layoutPosition));

        startActivity(startDetailActivityIntent, ActivityOptions.makeSceneTransitionAnimation(this, v.findViewById(R.id.iv_movie_poster), getString(R.string.transition_poster)).toBundle());

    }

    private static class FetchMoviesTask extends AsyncTask<URL, Void, ArrayList<Movie>> {

        private final WeakReference<MainActivity> activityReference;

        FetchMoviesTask(MainActivity context) {
            activityReference = new WeakReference<>(context);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            MainActivity activity = activityReference.get();
            if (activity == null || activity.isFinishing()) return;

            activity.showMovieData();
            activity.mLoadingIndicator.setVisibility(View.VISIBLE);
        }

        @Override
        protected ArrayList<Movie> doInBackground(URL... urls) {
            MainActivity activity = activityReference.get();
            if (activity == null || activity.isFinishing()) return null;

            URL queryUrl = urls[0];
            String movieQueryResults;
            try {
                movieQueryResults = NetworkUtils.getResponseFromHttpUrl(queryUrl);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }

            activity.movies = MovieDbJsonUtils.getMovieDataFromJson(movieQueryResults);

            return activity.movies;
        }

        @Override
        protected void onPostExecute(ArrayList<Movie> movies) {
            MainActivity activity = activityReference.get();
            if (activity == null || activity.isFinishing()) return;

            activity.mLoadingIndicator.setVisibility(View.INVISIBLE);
            if (movies != null) {
                activity.showMovieData();
                activity.mMovieAdapter.setMovieData(movies);
                activity.mMoviesRecyclerView.scrollToPosition(0);
            } else {
                activity.showErrorMessage();
            }
        }
    }
}
