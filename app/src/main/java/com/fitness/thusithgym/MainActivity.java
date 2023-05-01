package com.fitness.thusithgym;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.fitness.thusithgym.R;

public class MainActivity extends AppCompatActivity {
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // hide app name
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_main);

        // Connect splash screen to dashboard
        Thread thread = new Thread() {
            public void run() {
                try {
                    sleep(5000);
                    startActivity(new Intent(MainActivity.this, SignupActivity.class));
                    finish();

                } catch (Exception e) {
                }
            }

        }; thread.start();

    }
}
