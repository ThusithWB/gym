package com.fitness.thusithgym.activities;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.fitness.thusithgym.database.AppointmentActivity;
import com.fitness.thusithgym.database.DatabaseHelper;
import com.fitness.thusithgym.database.PaymentActivity;
import com.fitness.thusithgym.R;
import com.fitness.thusithgym.database.ScheduleActivity;
import com.fitness.thusithgym.database.UserInfoActivity;

public class DashboardActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ImageView menu;
    LinearLayout find_gym, home_workout, payment, instructor, info, heart, supplement, appointment;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_dashboard);

        // Retrieve name from database
        DatabaseHelper dbHelper = new DatabaseHelper(this, DatabaseHelper.DATABASE);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + DatabaseHelper.COL_NAME + " FROM " + DatabaseHelper.TABLE_NAME_USER_INFO, null);
        String name = "";
        if (cursor.moveToFirst()) {
            int columnIndex = cursor.getColumnIndex(DatabaseHelper.COL_NAME);
            if (columnIndex != -1) {
                name = cursor.getString(columnIndex);
            }
        }
        cursor.close();
        db.close();

        // create drawer navigation objects
        drawerLayout = findViewById(R.id.drawerLayout);
        menu = findViewById(R.id.menu);
        find_gym = findViewById(R.id.findGym);
        home_workout = findViewById(R.id.homeW);
        payment = findViewById(R.id.payment);
        instructor = findViewById(R.id.instructor);
        info = findViewById(R.id.info);
        heart = findViewById(R.id.heart);
        supplement = findViewById(R.id.supplement);
        appointment = findViewById(R.id.appointment);

        TextView welcomeTextView = findViewById(R.id.textName);
        String welcomeMessage = "Welcome " + name;
        welcomeTextView.setText(welcomeMessage);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.menu:
                        openDrawer(drawerLayout);
                        break;
                    case R.id.findGym:
                        redirectActivity(DashboardActivity.this, GymLocationActivity.class);
                        break;
                    case R.id.homeW:
                        redirectActivity(DashboardActivity.this, WorkoutActivity.class);
                        break;
                    case R.id.instructor:
                        redirectActivity(DashboardActivity.this, InstructorActivity.class);
                        break;
                    case R.id.info:
                        redirectActivity(DashboardActivity.this, UserInfoActivity.class);
                        break;
                    case R.id.supplement:
                        redirectActivity(DashboardActivity.this, FindSupplementActivity.class);
                        break;
                    case R.id.heart:
                        redirectActivity(DashboardActivity.this, HeartRateActivity.class);
                        break;
                    case R.id.appointment:
                        redirectActivity(DashboardActivity.this, AppointmentActivity.class);
                        break;
                    case R.id.payment:
                        redirectActivity(DashboardActivity.this, PaymentActivity.class);
                        break;
                    case R.id.btn_schedule:
                        Intent scheduleIntent = new Intent(DashboardActivity.this, ScheduleActivity.class);
                        startActivity(scheduleIntent);
                        break;
                    case R.id.walkingGoal:
                        Intent walkingIntent = new Intent(DashboardActivity.this, WalkActivity.class);
                        startActivity(walkingIntent);
                        break;
                    case R.id.buttonBMI:
                        Intent bmiIntent = new Intent(DashboardActivity.this, BMICalculatorActivity.class);
                        startActivity(bmiIntent);
                        break;
                }
            }
        };

        menu.setOnClickListener(onClickListener);
        find_gym.setOnClickListener(onClickListener);
        home_workout.setOnClickListener(onClickListener);
        instructor.setOnClickListener(onClickListener);
        info.setOnClickListener(onClickListener);
        supplement.setOnClickListener(onClickListener);
        heart.setOnClickListener(onClickListener);
        appointment.setOnClickListener(onClickListener);
        payment.setOnClickListener(onClickListener);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView schedule = findViewById(R.id.btn_schedule);
        schedule.setOnClickListener(onClickListener);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView walkingGoal = findViewById(R.id.walkingGoal);
        walkingGoal.setOnClickListener(onClickListener);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView buttonBMI = findViewById(R.id.buttonBMI);
        buttonBMI.setOnClickListener(onClickListener);
    }

        //open drawer navigation
        public static void openDrawer(DrawerLayout drawerLayout){
        drawerLayout.openDrawer(GravityCompat.START);
    }
    //close drawer navigation
    public static void closeDrawer(DrawerLayout drawerLayout){
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }
    public static void redirectActivity(Activity activity, Class secondActivity){
        Intent intent = new Intent(activity, secondActivity);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
        activity.finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
        closeDrawer(drawerLayout);
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

