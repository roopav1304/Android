package com.example.api2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

   // EditText editText = (EditText) findViewById(R.id.pageNo);
   // String editTextValue = editText.getText().toString();
    int page = 3;
    List<UsersList> usersList;
    Pages pageno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        getUsersList();
    }

    public void getUsersList() {

        APIInterface api = Api.getClient().create(APIInterface.class);

        Call<Pages> call = api.getUsersList(page);
       // Log.i("request url - ", call.request().url().toString());
        call.enqueue(new Callback<Pages>() {

            @Override
            public void onResponse(Call<Pages> call, Response<Pages> response) {

                pageno = response.body();
                usersList = pageno.getData();
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
                recyclerView.setLayoutManager(linearLayoutManager);
                // call the constructor of UsersAdapter to send the reference and data to Adapter
                UsersAdapter usersAdapter = new UsersAdapter(MainActivity.this, usersList);
                recyclerView.setAdapter(usersAdapter);

            }

            @Override
            public void onFailure(Call<Pages> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.toString(), Toast.LENGTH_LONG).show();
            }
        });


    }


}
