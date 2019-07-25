package com.example.restapis;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.StrictMode;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Callback;
import retrofit2.Call;
import retrofit2.Response;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.*;
import java.net.*;

public class MainActivity extends AppCompatActivity {


    MovieListResponse movieListResponseData;
    String id = "tt3896198";
    String apikey = "c4f0abdd";
    TextView title, released, director, actors, imdbRating, language;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        title = (TextView) findViewById(R.id.title);
        released = (TextView) findViewById(R.id.released);
        director = (TextView) findViewById(R.id.director);
        actors = (TextView) findViewById(R.id.actors);
        imdbRating = (TextView) findViewById(R.id.imdbRating);
        language = (TextView) findViewById(R.id.language);
        imageView = (ImageView) findViewById(R.id.img);
        getMovieDetails();
    }

    public void getMovieDetails() {


        ApiInterface api = Api.getClient().create(ApiInterface.class);
        Call<MovieListResponse> call = api.getMovie(id, apikey);

        Log.i("request url - ", call.request().url().toString());
        call.enqueue(new Callback<MovieListResponse>() {
            @Override
            public void onResponse(Call<MovieListResponse> call, Response<MovieListResponse> response) {

                //  Log.d("responseGET", response.body().getTitle());
                movieListResponseData = response.body();
                Log.i("qwwee- ", response.message().toString());
                title.setText("Title: " + movieListResponseData.getTitle());
                released.setText("Released: " + movieListResponseData.getReleased());
                director.setText("Director: " + movieListResponseData.getDirector());
                actors.setText("Actors: " + movieListResponseData.getActors());
                imdbRating.setText("IMDB Rating: " + movieListResponseData.getImdbRating());
                language.setText("Language: " + movieListResponseData.getLanguage());
                imageView.setImageBitmap(getBitmapFromURL(movieListResponseData.getPoster()));
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this , Photo.class);
                        intent.putExtra("IMAGE",movieListResponseData.getPoster());
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onFailure(Call<MovieListResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public static Bitmap getBitmapFromURL(String imgUrl) {
        try {
            URL url = new URL(imgUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            int SDK_INT = android.os.Build.VERSION.SDK_INT;

            if (SDK_INT > 8)
            {
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                        .permitAll().build();
                StrictMode.setThreadPolicy(policy);
                connection.connect();
            }
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            // Log exception
            return null;
        }
    }
}
