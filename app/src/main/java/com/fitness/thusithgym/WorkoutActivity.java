package com.fitness.thusithgym;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.thusithgym.R;

public class WorkoutActivity extends AppCompatActivity {
    private CardView cardView;
    private VideoView videoView;
    private MediaController mediaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_workout);

        // connect videos to cardViews
        cardView = (CardView) findViewById(R.id.basic1);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                playVideo(R.raw.basic1);
            }
        });

        cardView = (CardView) findViewById(R.id.basic2);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                playVideo(R.raw.basic2);
            }
        });

        cardView = (CardView) findViewById(R.id.basic3);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                playVideo(R.raw.basic3);
            }
        });

        cardView = (CardView) findViewById(R.id.basic4);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                playVideo(R.raw.basic4);
            }
        });

        cardView = (CardView) findViewById(R.id.basic5);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                playVideo(R.raw.basic5);
            }
        });

        cardView = (CardView) findViewById(R.id.basic6);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                playVideo(R.raw.basic6);
            }
        });


    }

    private void playVideo(int videoId) {
        setContentView(R.layout.activity_basic1);
        videoView = findViewById(R.id.videoView1);

        Uri uri = Uri.parse("android.resource://"+ getPackageName()+ "/" + videoId);
        videoView.setVideoURI(uri);
        videoView.start();

        mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);
    }
}