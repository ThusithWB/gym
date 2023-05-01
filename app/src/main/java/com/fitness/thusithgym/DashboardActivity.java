package com.fitness.thusithgym;

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
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.widget.LinearLayoutCompat;

public class DashboardActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ImageView menu;
    LinearLayout fGym, homeW, payment, instructor, info, heart, supplement, appointment;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_dashboard);

        //Retrieve name from database
        DatabaseHelper dbHelper = new DatabaseHelper(this, DatabaseHelper.DATABASE_NAME_2);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + DatabaseHelper.COL_NAME + " FROM " + DatabaseHelper.TABLE_NAME_2, null);
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
        fGym = findViewById(R.id.fGym);
        homeW = findViewById(R.id.homeW);
        payment = findViewById(R.id.payment);
        instructor = findViewById(R.id.instructor);
        info = findViewById(R.id.info);
        heart = findViewById(R.id.heart);
        supplement = findViewById(R.id.supplement);
        appointment = findViewById(R.id.appointment);

        TextView welcomeTextView = findViewById(R.id.textName);
        String welcomeMessage = "Welcome " + name;
        welcomeTextView.setText(welcomeMessage);


        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDrawer(drawerLayout);
            }
        });
        fGym.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redirectActivity(DashboardActivity.this, GymLocationActivity.class);
            }
        });
        homeW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redirectActivity(DashboardActivity.this, WorkoutActivity.class);
            }
        });
        instructor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redirectActivity(DashboardActivity.this, InstructorActivity.class);
            }
        });

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redirectActivity(DashboardActivity.this, UserInfoActivity.class);
            }
        });

        supplement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redirectActivity(DashboardActivity.this, FindSupplementActivity.class);
            }
        });

        heart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redirectActivity(DashboardActivity.this, HeartRateActivity.class);
            }
        });

        appointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redirectActivity(DashboardActivity.this, AppointmentActivity.class);
            }
        });

        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redirectActivity(DashboardActivity.this, PaymentActivity.class);
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView walkingGoal = findViewById(R.id.walkingGoal);
        walkingGoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, WalkActivity.class);
                startActivity(intent);
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView buttonBMI = findViewById(R.id.buttonBMI);
        buttonBMI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, BMICalculatorActivity.class);
                startActivity(intent);
            }
        });

    }
    public static void openDrawer(DrawerLayout drawerLayout){
        drawerLayout.openDrawer(GravityCompat.START);
    }
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