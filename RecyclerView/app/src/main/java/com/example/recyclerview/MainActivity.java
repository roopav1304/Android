package com.example.recyclerview;

import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;

    ArrayList<String> placesNames = new ArrayList<>();
    ArrayList<String> imagesURL = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.places);
        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(this));



        try {
            // get JSONObject from JSON file
                JSONObject obj1 = new JSONObject(loadJSONFromAsset());
                // fetch JSONArray named users
                JSONArray placesArray = obj1.getJSONArray("places");
                JSONArray imagesArray = obj1.getJSONArray("images");

                // implement for loop for getting users list data
                for (int i = 0; i < placesArray.length(); i++) {
                    // create a JSONObject for fetching single user data
                    JSONObject placesName = placesArray.getJSONObject(i);
                    // fetch name and store it in arraylist
                    placesNames.add(placesName.getString("name"));
                }
            for (int i = 0; i < imagesArray.length(); i++){
                JSONObject imagesURLs = imagesArray.getJSONObject(i);
                // fetch name and store it in arraylist
                imagesURL.add(imagesURLs.getString("url"));
            }

            } catch(JSONException e){
                e.printStackTrace();
            }
            mAdapter = new MyAdapter(MainActivity.this,placesNames, imagesURL);
            recyclerView.setAdapter(mAdapter);

            recyclerView.setHasFixedSize(true);

            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(mAdapter);

        }
    public String loadJSONFromAsset() {
        
        String json = null;
        AssetManager assetManager = getAssets();
        try {
            InputStream is = assetManager.open("json_ex.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return json;
    }
}
