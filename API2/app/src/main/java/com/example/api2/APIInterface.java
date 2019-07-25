package com.example.api2;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


    interface APIInterface {

        @GET("/api/users")
        Call<Pages> getUsersList(@Query("page") int page);

        @GET("/api/users/")
        Call<DataObject> getUser(@Query("id") int id);

    }

