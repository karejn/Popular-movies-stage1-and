# Popular-movies-stage1-and
Popular Movies Stage 1 (Project 2) - Udacity Android Developer Nanodegree

## Description
Made as part of Udacity's [Android Developer Nanodegree Program](https://www.udacity.com/course/android-developer-nanodegree-by-google--nd801).
This app:
- Presents the user with a grid arrangement of movie posters upon launch.
- Allows the user to change sort order via a setting:

    - The sort order can be by most popular or by highest-rated

- Allows the user to tap on a movie poster and transition to a details screen with additional information such as:

    - original title
    - movie poster image thumbnail
    - a plot synopsis (called overview in the api)
    - user rating (called vote_average in the api)
    - release date



## API Key
The movie information uses [The Movie Database (TMDb)](https://www.themoviedb.org/documentation/api) API.
To make your app work, you have to enter your own API key into `gradle.properties` file.

```gradle.properties
MOVIE_DB_API_KEY="Api Key"
