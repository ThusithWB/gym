package com.fitness.thusithgym.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import com.fitness.thusithgym.R;

public class ScheduleActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        listView = findViewById(R.id.listview_schedule);

        // Retrieve data from the appointment table
        DatabaseHelper dbHelper = new DatabaseHelper(this, DatabaseHelper.DATABASE);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_APPOINTMENT, null);

        ArrayList<String> scheduleList = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                // Extract data from each row
                int year = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_YEAR));
                int month = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_MONTH));
                int dayOfMonth = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_DAY_OF_Month));
                int hour = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_HOUR));
                int minute = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_MINUTE));

                // Format the date and time
                String date = String.format("%02d/%02d/%04d", dayOfMonth, month, year);
                String time = String.format("%02d:%02d", hour, minute);

                // Add to the schedule list
                scheduleList.add(date + " at " + time);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        // Display the schedule in a list view
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, scheduleList);
        listView.setAdapter(adapter);
    }
}