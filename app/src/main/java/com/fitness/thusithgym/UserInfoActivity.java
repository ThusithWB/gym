package com.fitness.thusithgym;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UserInfoActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    EditText enterName, enterAge, enterInstructor;
    Button btnInsert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Hide action bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_user_info);
        myDb = new DatabaseHelper(this, DatabaseHelper.DATABASE_NAME_2);

        //Define text box ids
        enterName = findViewById(R.id.editTextEnterName);
        enterAge = findViewById(R.id.editTextEnterAge);
        enterInstructor = findViewById(R.id.editTextEnterInstructor);

        //Define button ids
        btnInsert = findViewById(R.id.btn_insert);

        addData();
    }

    // implement addData() method to insert data
    public void addData(){
        btnInsert.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = myDb.insertUserInfo(enterName.getText().toString(), enterAge.getText().toString(),
                                enterInstructor.getText().toString());
                        if (isInserted == true)
                            Toast.makeText(UserInfoActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(UserInfoActivity.this, "Data not Inserted", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void onBackPressed() {
        Intent intent = new Intent(this, DashboardActivity.class);
        startActivity(intent);
        finish(); // to close the current activity
    }
}