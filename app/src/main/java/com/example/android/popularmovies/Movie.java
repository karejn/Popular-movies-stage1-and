package com.example.android.popularmovies;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ekzhu on 16.06.2018.
 */

public class Movie implements Parcelable {

    private int id;
    private String title;
    private String originalTitle;
    private int voteCount;
    private int voteAverage;
    private String releaseDate;
    private String description;
    private int popularity;
    private String posterPath;
    private String backdropPath;
    private boolean video;
    private String originalLanguage;
    private int[] genreIds;
    private boolean onlyForAdults;


    public Movie(int id, String title, String originalTitle, int voteCount, int voteAverage, String releaseDate, String description, int popularity, String posterPath, String backdropPath, boolean video, String originalLanguage, boolean onlyForAdults) {
        this.id = id;
        this.title = title;
        this.originalTitle = originalTitle;
        this.voteCount = voteCount;
        this.voteAverage = voteAverage;
        this.releaseDate = releaseDate;
        this.description = description;
        this.popularity = popularity;
        this.posterPath = posterPath;
        this.backdropPath = backdropPath;
        this.video = video;
        this.originalLanguage = originalLanguage;
        this.onlyForAdults = onlyForAdults;
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    private Movie(Parcel in) {
        id = in.readInt();
        title = in.readString();
        originalTitle = in.readString();
        voteCount = in.readInt();
        voteAverage = in.readInt();
        releaseDate = in.readString();
        description = in.readString();
        popularity = in.readInt();
        posterPath = in.readString();
        backdropPath = in.readString();
        video = in.readByte() != 0;
        originalLanguage = in.readString();
        onlyForAdults = in.readByte() != 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public int getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(int voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public boolean isVideo() {
        return video;
    }

    public void setVideo(boolean video) {
        this.video = video;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public boolean isOnlyForAdults() {
        return onlyForAdults;
    }

    public void setOnlyForAdults(boolean onlyForAdults) {
        this.onlyForAdults = onlyForAdults;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Flatten this object in to a Parcel.
     *
     * @param dest  The Parcel in which the object should be written.
     * @param flags Additional flags about how the object should be written.
     *              May be 0 or {@link #PARCELABLE_WRITE_RETURN_VALUE}.
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(originalTitle);
        dest.writeInt(voteCount);
        dest.writeInt(voteAverage);
        dest.writeString(releaseDate);
        dest.writeString(description);
        dest.writeInt(popularity);
        dest.writeString(posterPath);
        dest.writeString(backdropPath);
        dest.writeByte((byte) (video ? 1 : 0));
        dest.writeString(originalLanguage);
        dest.writeByte((byte) (onlyForAdults ? 1 : 0));
    }
}
