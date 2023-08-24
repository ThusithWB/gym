package com.fitness.thusithgym.database;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.fitness.thusithgym.R;
import com.fitness.thusithgym.activities.DashboardActivity;

public class UserInfoActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    EditText enterName, enterAge, deleteUser;
    Button btnInsert, btnUpdate, btnDelete;
    Spinner spinner;
    String[] instructor = {"Tom", "William", "Amanda"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Hide action bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_user_info);
        myDb = new DatabaseHelper(this, DatabaseHelper.DATABASE);

        //Define text box ids
        enterName = findViewById(R.id.editTextEnterName);
        enterAge = findViewById(R.id.editTextEnterAge);
        deleteUser = findViewById(R.id.editTextTextDelete);

        spinner = findViewById(R.id.editTextEnterInstructor);

        //Define button ids
        btnInsert = findViewById(R.id.btn_insert);
        btnUpdate = findViewById(R.id.btn_update);
        btnDelete = findViewById(R.id.btn_delete);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(UserInfoActivity.this, android.R.layout.simple_spinner_dropdown_item,instructor);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String value = parent.getItemAtPosition(position).toString();
                Toast.makeText(UserInfoActivity.this, value,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        addData();
        updateData();
        deleteData();
    }

    // implement addData() method to insert data
    public void addData() {
        btnInsert.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String selectedItem = spinner.getSelectedItem().toString();
                        boolean isInserted = myDb.insertUserInfo(
                                enterName.getText().toString(),
                                enterAge.getText().toString(),
                                selectedItem
                        );
                        if (isInserted)
                            Toast.makeText(UserInfoActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(UserInfoActivity.this, "Data not Inserted", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    // implement updateData() method to update data
    private void updateData() {
        btnUpdate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String selectedItem = spinner.getSelectedItem().toString();
                        boolean isUpdate = myDb.updateData(
                                enterName.getText().toString(),
                                enterAge.getText().toString(),
                                selectedItem
                        );
                        if (isUpdate)
                            Toast.makeText(UserInfoActivity.this, "Task Updated", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(UserInfoActivity.this, "Task Not Updated", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    // implement deleteData() method to Delete data
    public void deleteData(){
        btnDelete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer deleteatarows = myDb.deleteData(deleteUser.getText().toString());
                        if (deleteatarows > 0)
                            Toast.makeText(UserInfoActivity.this, "User Deleted", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(UserInfoActivity.this, "User not Deleted", Toast.LENGTH_LONG).show();
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