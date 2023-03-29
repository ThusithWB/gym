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

public class InstructorActivity extends AppCompatActivity {
    private CardView cardView;
    private VideoView videoView;
    private MediaController mediaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_instructor);

        // connect videos to cardView
        cardView = (CardView) findViewById(R.id.coach1);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playVideo(R.raw.coach1);
            }
        });

        cardView = (CardView) findViewById(R.id.coach2);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playVideo(R.raw.coach2);
            }
        });

        cardView = (CardView) findViewById(R.id.coach3);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playVideo(R.raw.coach3);
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