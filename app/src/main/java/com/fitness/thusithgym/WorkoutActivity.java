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
import com.google.android.material.bottomnavigation.BottomNavigationView;

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
        CardView cardView1 = findViewById(R.id.basic1);
        CardView cardView2 = findViewById(R.id.basic2);
        CardView cardView3 = findViewById(R.id.basic3);
        CardView cardView4 = findViewById(R.id.basic4);
        CardView cardView5 = findViewById(R.id.basic5);
        CardView cardView6 = findViewById(R.id.basic6);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int videoId = 0;
                switch (v.getId()) {
                    case R.id.basic1:
                        videoId = R.raw.basic1;
                        break;
                    case R.id.basic2:
                        videoId = R.raw.basic2;
                        break;
                    case R.id.basic3:
                        videoId = R.raw.basic3;
                        break;
                    case R.id.basic4:
                        videoId = R.raw.basic4;
                        break;
                    case R.id.basic5:
                        videoId = R.raw.basic5;
                        break;
                    case R.id.basic6:
                        videoId = R.raw.basic6;
                        break;
                }
                playVideo(videoId);
            }
        };

        cardView1.setOnClickListener(listener);
        cardView2.setOnClickListener(listener);
        cardView3.setOnClickListener(listener);
        cardView4.setOnClickListener(listener);
        cardView5.setOnClickListener(listener);
        cardView6.setOnClickListener(listener);

        //bottom navigation
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setSelectedItemId(R.id.bottom_workout);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.bottom_workout:
                    return true;
                case R.id.bottom_home:
                    startActivity(new Intent(getApplicationContext(), DashboardActivity.class));
                    finish();
                    return true;
                case R.id.bottom_supplement:
                    startActivity(new Intent(getApplicationContext(), FindSupplementActivity.class));
                    finish();
                    return true;
                case R.id.bottom_heartRate:
                    startActivity(new Intent(getApplicationContext(), HeartRateActivity.class));
                    finish();
                    return true;
            }
            return false;
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

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, DashboardActivity.class);
        startActivity(intent);
        finish(); // to close the current activity
    }

}