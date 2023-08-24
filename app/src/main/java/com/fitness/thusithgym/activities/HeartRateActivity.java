package com.fitness.thusithgym.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import com.fitness.thusithgym.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HeartRateActivity extends AppCompatActivity {
    private TextView heartRateTextView;
    private SensorManager sensorManager;
    private Sensor heartRateSensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heart_rate);

        heartRateTextView = (TextView) findViewById(R.id.heart_rate_text_view);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        heartRateSensor = sensorManager.getDefaultSensor(Sensor.TYPE_HEART_RATE);

        if (heartRateSensor == null) {
            heartRateTextView.setText("Heart rate sensor not found");
        } else {
            sensorManager.registerListener(sensorEventListener, heartRateSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }

        //Bottom navigation bar
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setSelectedItemId(R.id.bottom_heartRate);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.bottom_workout:
                    startActivity(new Intent(getApplicationContext(), WorkoutActivity.class));
                    finish();
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
                    return true;
            }
            return false;
        });
    }

    //Heart rate monitor
    private SensorEventListener sensorEventListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            float heartRate = event.values[0];
            heartRateTextView.setText("Heart rate: " + (int)heartRate + " bpm");
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, DashboardActivity.class);
        startActivity(intent);
        finish(); // to close the current activity
    }
}
