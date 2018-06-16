package com.example.android.popularmovies;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by ekzhu on 16.06.2018.
 */

public final class NetworkUtils {

    private final static String MOVIEDB_BASE_URL = "http://api.themoviedb.org/3";
    private final static String PATH_MOVIES = "movie";
    private final static String PATH_SORT_POPULAR = "popular";
    private final static String PATH_SORT_TOP_RATED = "top_rated";
    private final static String PARAM_QUERY = "api_key";

    private final static String MOVIEDB_IMAGE_BASE_URL = "http://image.tmdb.org/t/p/";
    private final static String PATH_IMAGE_WIDTH = "w342";


    public static URL buildURL(int menuSelection) {

        Uri builtUri = null;

        switch (menuSelection) {
            case R.id.menu_most_popular:
                builtUri = Uri.parse(MOVIEDB_BASE_URL).buildUpon()
                        .appendPath(PATH_MOVIES)
                        .appendPath(PATH_SORT_POPULAR)
                        .appendQueryParameter(PARAM_QUERY, BuildConfig.MOVIE_DB_API_KEY)
                        .build();
                break;
            case R.id.menu_top_rated:
                builtUri = Uri.parse(MOVIEDB_BASE_URL).buildUpon()
                        .appendPath(PATH_MOVIES)
                        .appendPath(PATH_SORT_TOP_RATED)
                        .appendQueryParameter(PARAM_QUERY, BuildConfig.MOVIE_DB_API_KEY)
                        .build();
                break;
        }

        URL url = null;
        try {
            url = new URL(builtUri != null ? builtUri.toString() : null);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    public static String buildImageUrl(String movieImagePath) {
        Uri builtUri = Uri.parse(MOVIEDB_IMAGE_BASE_URL).buildUpon()
                .appendPath(PATH_IMAGE_WIDTH)
                .appendEncodedPath(movieImagePath)
                .build();

        return builtUri.toString();
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream inputStream = urlConnection.getInputStream();

            Scanner scanner = new Scanner(inputStream);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}
