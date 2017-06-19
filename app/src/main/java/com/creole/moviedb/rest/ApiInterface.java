package com.creole.moviedb.rest;

import com.creole.moviedb.model.GenreResponse;
import com.creole.moviedb.model.ImageResponse;
import com.creole.moviedb.model.MovieResponse;
import com.creole.moviedb.model.VideoResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by admin1234 on 5/31/17.
 */

public interface ApiInterface {

    //https://api.themoviedb.org/3/Movie/top_rated
    @GET("movie/top_rated")
    Call<MovieResponse> getTopRatedMovies(@Query("api_key") String apiKey,
                                          @Query("language") String language,
                                          @Query("page") int page);

    @GET("genre/movie/list")
    Call<GenreResponse> getGenres(@Query("api_key") String apiKey,
                                  @Query("language") String language);

    /// genre/movie/list?api_key=f66b41ed42f7671a7c9623f312add642&language=en-US

    @GET("movie/{movie_id}/images")
    Call<ImageResponse> getImages(@Path("movie_id") int id,
                                  @Query("api_key") String apiKey,
                                  @Query("language") String language,
                                  @Query("include_image_language") String includeImageLanguage);


    @GET("movie/{movie_id}/videos")
    Call<VideoResponse> getVideos(@Path("movie_id") int id,
                                  @Query("api_key") String apiKey,
                                  @Query("language") String language);

    @GET("movie/{id}")
    Call<MovieResponse> getMovieDetails(@Path("id") int id, @Query("api_key") String apiKey);

}

