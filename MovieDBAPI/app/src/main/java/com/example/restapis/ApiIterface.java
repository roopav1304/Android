package com.example.restapis;

import java.util.List;

import retrofit2.Callback;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.Call;

    interface ApiInterface {

        @GET("/")
        Call<MovieListResponse> getMovie(@Query("i") String id , @Query("apikey") String api_key);

    }