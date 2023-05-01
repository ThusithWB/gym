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

import com.fitness.thusithgym.R;

public class InstructorActivity extends AppCompatActivity {
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
        CardView cardView1 = findViewById(R.id.coach1);
        CardView cardView2 = findViewById(R.id.coach2);
        CardView cardView3 = findViewById(R.id.coach3);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int videoId = 0;
                switch (v.getId()) {
                    case R.id.coach1:
                        videoId = R.raw.coach1;
                        break;
                    case R.id.coach2:
                        videoId = R.raw.coach2;
                        break;
                    case R.id.coach3:
                        videoId = R.raw.coach3;
                        break;
                }
                playVideo(videoId);
            }
        };

        cardView1.setOnClickListener(listener);
        cardView2.setOnClickListener(listener);
        cardView3.setOnClickListener(listener);

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

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, DashboardActivity.class);
        startActivity(intent);
        finish(); // Optional: call finish() to close the current activity
    }

}
