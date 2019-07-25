package com.example.youtubeapi;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends YouTubeBaseActivity {

    YouTubePlayerView youTubePlayerView;
    Button play;
    YouTubePlayer.OnInitializedListener onInitializedListener;

    String apiKey = "AIzaSyD4EA4iKcWWDKsrHSWJYka6MhzWAn8xNMk";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        youTubePlayerView = (YouTubePlayerView) findViewById(R.id.youtubeVideoPlay);
        play = (Button) findViewById(R.id.playbutton);

        onInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                List<String> videoList = new ArrayList<>();
                videoList.add("W4hTJybfU7s");
                videoList.add("a4NT5iBFuZs");
                videoList.add("e0TEpt2c7bU");
                youTubePlayer.loadVideos(videoList);
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                Toast toast = Toast.makeText(MainActivity.this,"Vedio play failed",Toast.LENGTH_SHORT);
            }
        };
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                youTubePlayerView.initialize(apiKey,onInitializedListener);
            }
        });
    }
}
