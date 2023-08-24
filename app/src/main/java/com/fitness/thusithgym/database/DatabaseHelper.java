package com.fitness.thusithgym.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE = "Fitness.db";
    public static final String TABLE_NAME_ALL_USERS = "allusers";
    public static final String COL_EMAIL = "email";
    public static final String COL_PASSWORD = "password";
    public static final String TABLE_NAME_USER_INFO = "userinfo";
    public static final String COL_NAME = "name";
    public static final String COL_AGE = "age";
    public static final String COL_INSTRUCTOR = "instructor";
    public static final String TABLE_APPOINTMENT = "appointment";
    public static final String COLUMN_YEAR = "year";
    public static final String COLUMN_MONTH = "month";
    public static final String COLUMN_DAY_OF_Month = "dayOfMonth";
    public static final String COLUMN_HOUR = "hour";
    public static final String COLUMN_MINUTE = "minute";

    public DatabaseHelper(@Nullable Context context, String databaseName) {
        super(context, DATABASE, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table " + TABLE_NAME_ALL_USERS + " (" + COL_EMAIL + " TEXT primary key, " + COL_PASSWORD + " TEXT)");
        db.execSQL("create table " + TABLE_NAME_USER_INFO + " (" + COL_NAME + " TEXT, " + COL_AGE + " TEXT, " + COL_INSTRUCTOR + " TEXT)");
        db.execSQL("create table " + TABLE_APPOINTMENT + " (" + COLUMN_YEAR + " INT, " + COLUMN_MONTH + " INT, " + COLUMN_DAY_OF_Month + " INT, " + COLUMN_HOUR + " INT, " + COLUMN_MINUTE + " INT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists " + TABLE_NAME_ALL_USERS);
        db.execSQL("drop table if exists " + TABLE_NAME_USER_INFO);
        db.execSQL("drop table if exists " + TABLE_APPOINTMENT);
        onCreate(db);
    }

    public boolean insertData(String email, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_EMAIL, email);
        contentValues.put(COL_PASSWORD, password);
        long result = db.insert(TABLE_NAME_ALL_USERS, null, contentValues);
        db.close();

        return result != -1;
    }

    //Insert information provided by user
    public boolean insertUserInfo(String name, String age, String instructor){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_NAME, name);
        contentValues.put(COL_AGE, age);
        contentValues.put(COL_INSTRUCTOR, instructor);
        long result = db.insert(TABLE_NAME_USER_INFO, null, contentValues);
        db.close();

        return result != -1;
    }

    // Update data implementation
    public boolean updateData(String name, String age, String instructor){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_NAME,name);
        contentValues.put(COL_AGE,age);
        contentValues.put(COL_INSTRUCTOR,instructor);
        db.update(TABLE_NAME_USER_INFO, contentValues, "name=?", new String[] {name});
        return true;
    }

    //Delete data if required
    public Integer deleteData(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME_USER_INFO, "name = ?", new String[] {name});
    }

    //Appointment booking
    public boolean insertAppointment(int year, int month, int dayOfMonth, int hour, int minute) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_YEAR, year);
        contentValues.put(COLUMN_MONTH, month);
        contentValues.put(COLUMN_DAY_OF_Month, dayOfMonth);
        contentValues.put(COLUMN_HOUR, hour);
        contentValues.put(COLUMN_MINUTE, minute);
        long result = db.insert(TABLE_APPOINTMENT, null, contentValues);
        db.close();

        return result != -1;
    }

    public boolean checkEmail(String email){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from " + TABLE_NAME_ALL_USERS + " where " + COL_EMAIL + " = ?", new String[]{email});

        boolean exists = cursor.getCount() > 0;
        cursor.close();
        //db.close();

        return exists;
    }

    public boolean checkEmailPassword(String email, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from " + TABLE_NAME_ALL_USERS + " where " + COL_EMAIL + " = ? and " + COL_PASSWORD + " = ?", new String[]{email, password});

        boolean exists = cursor.getCount() > 0;
        cursor.close();
        //db.close()

        return exists;
    }

}
