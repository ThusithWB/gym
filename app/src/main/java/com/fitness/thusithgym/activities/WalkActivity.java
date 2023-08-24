package com.fitness.thusithgym.activities;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.fitness.thusithgym.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class WalkActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor accelerometer;
    private int stepsCount = 0;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_walk);

        // Get an instance of the sensor service
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        // Get the default sensor of the device
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        textView = findViewById(R.id.steps_count_textview);

        // Get the reset button
        Button resetButton = findViewById(R.id.reset_button);
        // Set a click listener for the reset button
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Reset the step count and update the text view
                stepsCount = 0;
                textView.setText("Steps Count: " + stepsCount);
            }
        });

        //Bottom navigation bar implementation
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.getMenu().clear(); // Clear the selection
        bottomNavigationView.inflateMenu(R.menu.bottom_menu);
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
                    startActivity(new Intent(getApplicationContext(), HeartRateActivity.class));
                    finish();
                    return true;
            }
            return false;
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Register this class as a listener for the accelerometer sensor
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Unregister this class as a listener for the accelerometer sensor
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float[] values = event.values;
        float x = values[0];
        float y = values[1];
        float z = values[2];

        // Calculate the magnitude of the acceleration vector
        float accelerationMagnitude = (float) Math.sqrt(x * x + y * y + z * z);

        // Check if the acceleration magnitude is above a threshold value
        if (accelerationMagnitude > 10) {
            stepsCount++;
            textView.setText("Steps Count: " + stepsCount);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Do nothing
    }
}
