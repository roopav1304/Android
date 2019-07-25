package com.example.api2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.api2.UsersAdapter.getBitmapFromURL;

public class Details extends AppCompatActivity {

    TextView firstname,lastname,email;
    ImageView imageView;

    DataObject userData;
    UserData user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        imageView = (ImageView) findViewById(R.id.img1);
        firstname = (TextView)findViewById(R.id.first_name1);
        lastname = (TextView)findViewById(R.id.last_name1);
        email = (TextView)findViewById(R.id.email1);

        Intent intent = getIntent();
        int id = intent.getIntExtra("id",1);

        APIInterface api = Api.getClient().create(APIInterface.class);
        Call<DataObject> call = api.getUser(id);
        Log.i("request url - ", call.request().url().toString());

        call.enqueue(new Callback<DataObject>() {
            @Override
            public void onResponse(Call<DataObject> call, Response<DataObject> response) {
                userData = response.body();
                user = userData.getData();
                imageView.setImageBitmap(getBitmapFromURL(user.getAvatar()));
                firstname.setText("First Name : "+user.getFirstName());
                lastname.setText("Last Name : "+user.getLastName());
                email.setText("Email : "+user.getEmail());
            }

            @Override
            public void onFailure(Call<DataObject> call, Throwable t) {
                Toast.makeText(Details.this, t.toString(), Toast.LENGTH_LONG).show();

            }
        });



    }
}
