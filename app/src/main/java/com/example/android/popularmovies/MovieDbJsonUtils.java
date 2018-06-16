package com.example.android.popularmovies;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by ekzhu on 16.06.2018.
 */

public final class MovieDbJsonUtils {


    public static ArrayList<Movie> getMovieDataFromJson(String movieJsonString) {
        ArrayList<Movie> movieList = new ArrayList<>();

        final String RESULTS = "results";
        final String ID = "id";
        final String TITLE = "title";
        final String ORIGINAL_TITLE = "original_title";
        final String VOTE_COUNT = "vote_count";
        final String VOTE_AVERAGE = "vote_average";
        final String RELEASE_DATE = "release_date";
        final String OVERVIEW = "overview";
        final String POPULARITY = "popularity";
        final String POSTER_PATH = "poster_path";
        final String BACKDROP_PATH = "backdrop_path";
        final String VIDEO = "video";
        final String ORIGINAL_LANGUAGE = "original_language";
        final String ADULT = "adult";


        JSONObject movies;
        try {
            movies = new JSONObject(movieJsonString);

            JSONArray results = movies.getJSONArray(RESULTS);

            for (int i = 0; i < results.length(); i++) {
                JSONObject movie = results.getJSONObject(i);

                int id = movie.getInt(ID);
                String title = movie.getString(TITLE);
                String originalTitle = movie.getString(ORIGINAL_TITLE);
                int voteCount = movie.getInt(VOTE_COUNT);
                int voteAverage = movie.getInt(VOTE_AVERAGE);
                String releaseDate = movie.getString(RELEASE_DATE);
                String description = movie.getString(OVERVIEW);
                int popularity = movie.getInt(POPULARITY);
                String posterPath = movie.getString(POSTER_PATH);
                String backdropPath = movie.getString(BACKDROP_PATH);
                boolean video = movie.getBoolean(VIDEO);
                String originalLanguage = movie.getString(ORIGINAL_LANGUAGE);

                boolean onlyForAdults = movie.getBoolean(ADULT);

                movieList.add(i, new Movie(
                        id,
                        title,
                        originalTitle,
                        voteCount,
                        voteAverage,
                        releaseDate,
                        description,
                        popularity,
                        posterPath,
                        backdropPath,
                        video,
                        originalLanguage,
                        onlyForAdults));
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return movieList;
    }
}
