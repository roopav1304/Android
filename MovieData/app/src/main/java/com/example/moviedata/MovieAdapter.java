package com.example.moviedata;

import android.support.v7.widget.RecyclerView;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.StrictMode;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;


public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    Context context;
    List<Results> results;

    public MovieAdapter(Context context,List<Results> results){
        this.context = context;
        this.results = results;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.list_view, null);
        ViewHolder usersViewHolder = new ViewHolder(view);
        return usersViewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // set the data
        holder.title.setText("Title : " + results.get(position).gettitle());
        holder.rating.setText("Rating : "+results.get(position).getvote_average());
        String url = "https://image.tmdb.org/t/p/w500"+results.get(position).getposter_path();
        Log.i("image url-",url);
        holder.imageView.setImageBitmap(getBitmapFromURL(url));
    }

    @Override
    public int getItemCount() {
        return results.size(); // size of the list items
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        // init the item view's
        TextView title,rating;
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            // get the reference of item view's
            title = (TextView) itemView.findViewById(R.id.title);
            rating = (TextView) itemView.findViewById(R.id.rating);
            imageView = (ImageView) itemView.findViewById(R.id.img);
        }
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


