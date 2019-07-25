package com.example.moviedata;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

interface Interface {

    @GET("/3/discover/movie")
    Call<MovieData> getMoviesList(@Query("api_key") String api_key , @Query("primary_release_year") int year , @Query("page") int page);
}
