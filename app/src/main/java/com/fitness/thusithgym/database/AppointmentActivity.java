package com.fitness.thusithgym.database;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import com.fitness.thusithgym.R;
import com.fitness.thusithgym.activities.DashboardActivity;
import com.fitness.thusithgym.activities.FindSupplementActivity;
import com.fitness.thusithgym.activities.HeartRateActivity;
import com.fitness.thusithgym.activities.WorkoutActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Calendar;

public class AppointmentActivity extends AppCompatActivity {

    private DatePicker datePicker;
    private TimePicker timePicker;
    private Button bookAppointmentButton;

    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_appointment);

        dbHelper = new DatabaseHelper(this, DatabaseHelper.TABLE_APPOINTMENT);

        // Initialize UI elements
        datePicker = findViewById(R.id.date_picker);
        timePicker = findViewById(R.id.time_picker);
        bookAppointmentButton = findViewById(R.id.button_book_appointment);

        // Set minimum date to today
        Calendar calendar = Calendar.getInstance();
        datePicker.setMinDate(calendar.getTimeInMillis());

        // Set click listener for the book appointment button
        bookAppointmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get selected date and time
                int year = datePicker.getYear();
                int month = datePicker.getMonth() + 1; // DatePicker month is zero-based
                int dayOfMonth = datePicker.getDayOfMonth();
                int hour = timePicker.getCurrentHour();
                int minute = timePicker.getCurrentMinute();

                // Insert appointment data into the database
                boolean success = dbHelper.insertAppointment(year, month, dayOfMonth, hour, minute);

                if (success) {
                    // Show appointment booked message
                    String message = String.format("Appointment booked for %02d/%02d/%04d at %02d:%02d", dayOfMonth, month, year, hour, minute);
                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Failed to book appointment.", Toast.LENGTH_SHORT).show();
                }
            }
        });

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
}