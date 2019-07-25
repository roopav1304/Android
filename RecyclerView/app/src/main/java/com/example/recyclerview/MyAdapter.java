package com.example.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import android.os.StrictMode;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;

import android.widget.TextView;
import android.widget.RelativeLayout;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.*;
import java.net.*;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    ArrayList<String> placesNames = new ArrayList<>();
    ArrayList<String> imagesURL = new ArrayList<>();
    public Context context;

    public MyAdapter(Context context,ArrayList<String> placesNames, ArrayList<String> imagesURL) {
       this.placesNames = placesNames;
       this.imagesURL = imagesURL;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.list_view, parent, false);

        ViewHolder vh = new ViewHolder(listItem);
        return vh;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder  {

        public TextView textView;
        public ImageView imageView;
        public RelativeLayout relativeLayout;

        public ViewHolder(View itemView) {

            super(itemView);
            this.textView = (TextView) itemView.findViewById(R.id.textView);
            this.imageView = (ImageView) itemView.findViewById(R.id.img);
            relativeLayout = (RelativeLayout)itemView.findViewById(R.id.relative_layout);
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        holder.textView.setText(placesNames.get(position));
        holder.imageView.setImageBitmap(getBitmapFromURL(imagesURL.get(position)));
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence text = "Hello " + placesNames.get(position) + " !";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return placesNames.size();
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
