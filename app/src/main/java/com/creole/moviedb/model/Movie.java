package com.creole.moviedb.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.Attributes;

/**
 * Created by admin1234 on 5/31/17.
 */

public class Movie implements Serializable{
    @SerializedName("release_date")
    private String releaseDate;
    @SerializedName("original_language")
    private String originalLanguage;
    @SerializedName("vote_average")
    private Double voteAverage;
    @SerializedName("original_title")
    private String originalTitle;
    @SerializedName("title")
    private String title;
    @SerializedName("poster_path")
    private String posterPath;
    @SerializedName("id")
    private Integer id;
    @SerializedName("overview")
    private String overview;
    @SerializedName("genre_ids")
    private List<Integer> genres = new ArrayList();
    @SerializedName("posters")
    private List<String> images = new ArrayList();
    @SerializedName("backdrop_path")
    private String backdropPath;



    private String genresName;



    public Movie(String releaseDate, String originalLanguage,
                 Double voteAverage, String originalTitle, String title,
                 String posterPath, Integer id, String overview,
                 List<Integer> genres, List<String> images, String backdropPath) {
        this.releaseDate = releaseDate;
        this.originalLanguage = originalLanguage;
        this.voteAverage = voteAverage;
        this.originalTitle = originalTitle;
        this.title = title;
        this.posterPath = posterPath;
        this.id = id;
        this.overview = overview;
        this.genres = genres;
        this.images = images;
        this.backdropPath = backdropPath;

    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }


    public List<Integer> getGenreIds() {
        return genres;
    }

    public void setGenreIds(List<Integer> genres) {
        this.genres = genres;
    }

    public String getGenreToString() {
        return this.genres.toString();
    }

    public String getGenresName() {
        return genresName;
    }

    public void setGenresName(List<Genre> genreList) {
        //id
        // genres;
        StringBuilder sb = new StringBuilder();
        for (Integer a : genres) {
            for (Genre genre : genreList) {
                if (a == (genre.getId())) {
//                    genresName=genre.getName()+",";
                    sb.append(genre.getName()).append(",");
                    break;
                }
            }

        }
        sb.deleteCharAt(sb.length()-(sb.length()-sb.lastIndexOf(",")));

        this.genresName = sb.toString();
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }
}

