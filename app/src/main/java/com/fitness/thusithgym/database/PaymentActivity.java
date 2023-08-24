package com.fitness.thusithgym.database;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

import com.fitness.thusithgym.R;
import com.fitness.thusithgym.activities.DashboardActivity;
import com.fitness.thusithgym.database.DatabaseHelper;

public class PaymentActivity extends AppCompatActivity {
    private TextView priceTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        priceTextView = findViewById(R.id.priceTextView);

        String instructor = getSelectedInstructor();
        int price = calculatePrice(instructor);

        String priceText = "Rs " + price;
        priceTextView.setText(priceText);
    }

    private String getSelectedInstructor() {
        String instructor = "";
        DatabaseHelper dbHelper = new DatabaseHelper(this, DatabaseHelper.DATABASE);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + DatabaseHelper.COL_INSTRUCTOR + " FROM " + DatabaseHelper.TABLE_NAME_USER_INFO, null);
        if (cursor.moveToFirst()) {
            int columnIndex = cursor.getColumnIndex(DatabaseHelper.COL_INSTRUCTOR);
            if (columnIndex != -1) {
                instructor = cursor.getString(columnIndex);
            }
        }
        cursor.close();
        db.close();
        return instructor;
    }

    // Calculate the monthly fee according to instructor
    private int calculatePrice(String instructor) {
        int price = 0;
        if (instructor.equalsIgnoreCase("William")) {
            price = 2200;
        } else if (instructor.equalsIgnoreCase("Tom")) {
            price = 2000;
        } else if (instructor.equalsIgnoreCase("Amanda")) {
            price = 2500;
        }
        return price;
    }

    public void onBackPressed() {
        Intent intent = new Intent(this, DashboardActivity.class);
        startActivity(intent);
        finish(); // to close the current activity
    }
}
