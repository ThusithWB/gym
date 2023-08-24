package com.fitness.thusithgym.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.fitness.thusithgym.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BMICalculatorActivity extends AppCompatActivity {

    private EditText weightEditText;
    private EditText heightEditText;
    private TextView bmiTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_bmi_calculator);

        weightEditText = findViewById(R.id.weightEditText);
        heightEditText = findViewById(R.id.heightEditText);
        bmiTextView = findViewById(R.id.bmiTextView);

        Button calculateButton = findViewById(R.id.calculateButton);
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateBMI();
            }
        });

        //bottom navigation bar
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

    //BMI Calculator
    private void calculateBMI() {
        String weightString = weightEditText.getText().toString();
        String heightString = heightEditText.getText().toString();

        if (TextUtils.isEmpty(weightString) || TextUtils.isEmpty(heightString)) {
            Toast.makeText(this, "Please enter both weight and height", Toast.LENGTH_SHORT).show();
            return;
        }

        double weight = Double.parseDouble(weightString);
        double height = Double.parseDouble(heightString);
        double bmi = weight / (height * height);

        bmiTextView.setText(getString(R.string.bmi_result, bmi));
    }
}
