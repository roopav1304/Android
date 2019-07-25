package com.example.moviedata;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.app.ProgressDialog;

import java.util.List;
import android.util.Log;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    int page = 1;
    String api_key = "f63b434c2eae10c869016441dc039f7b";
    int year = 2018;
    List<Results> moviesList;
    MovieData movieData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        getMoviesList();
    }

    public void getMoviesList(){

        final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show();

        Interface api = API.getClient().create(Interface.class);

        Call<MovieData> call = api.getMoviesList(api_key,year,page);
        Log.i("request url - ", call.request().url().toString());
        call.enqueue(new Callback<MovieData>() {
            @Override
            public void onResponse(Call<MovieData> call, Response<MovieData> response) {

                movieData = response.body();
                moviesList = movieData.getResults();
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
                recyclerView.setLayoutManager(linearLayoutManager);
                // call the constructor of UsersAdapter to send the reference and data to Adapter
                MovieAdapter movieAdapter = new MovieAdapter(MainActivity.this, moviesList);
                recyclerView.setAdapter(movieAdapter);
                progressDialog.hide();
            }

            @Override
            public void onFailure(Call<MovieData> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.toString(), Toast.LENGTH_LONG).show();
                progressDialog.hide();
            }
        });

    }
}
