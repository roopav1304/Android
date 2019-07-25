package com.example.api2;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.StrictMode;
import android.support.v7.widget.RecyclerView;
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

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.ViewHolder> {

    Context context;
    List<UsersList> usersList;

    public UsersAdapter(Context context, List<UsersList> usersList){
        this.context = context;
        this.usersList = usersList;
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
        holder.firstname.setText("First Name : " + usersList.get(position).getFirstName());
        holder.imageView.setImageBitmap(getBitmapFromURL(usersList.get(position).getAvatar()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,Details.class);
                intent.putExtra("id",usersList.get(position).getId());
                context.startActivity(intent);
            }
        });

        }

        @Override
        public int getItemCount() {
            return usersList.size(); // size of the list items
        }

    class ViewHolder extends RecyclerView.ViewHolder {
    // init the item view's
    TextView firstname;
    ImageView imageView;


    public ViewHolder(View itemView) {
        super(itemView);
        // get the reference of item view's
        firstname = (TextView) itemView.findViewById(R.id.fist_name);
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
