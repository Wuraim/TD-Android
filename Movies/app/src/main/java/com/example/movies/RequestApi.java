package com.example.movies;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RequestApi {
    public static final String ENDPOINT = "https://api.themoviedb.org/3/";

    @GET("movie/popular")
    Call<MoviesList> searchPopular(@Query("api_key") String api_key);

    @GET("movie/upcoming")
    Call<MoviesList> searchUpcoming(@Query("api_key") String api_key);

    @GET("movie/{id}")
    Call<Movie> searchDescription(@Path("id") int id,@Query("api_key") String api_key);

    @GET("search/movie")
    Call<MoviesList> searchMovie(@Query("api_key") String api_key,@Query("query") String query);

}
