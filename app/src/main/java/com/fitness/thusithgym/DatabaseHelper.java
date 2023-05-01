package com.fitness.thusithgym;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String databaseName = "Signup.db";

    public static final String DATABASE_NAME_2 = "UserInfo.db";
    public static final String TABLE_NAME_2 = "userinfo";
    public static final String COL_NAME = "name";
    public static final String COL_AGE = "age";
    public static final String COL_INSTRUCTOR = "instructor";

    public DatabaseHelper(@Nullable Context context, String dbName) {

        super(context, "Signup.db", null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("create table if not exists " + TABLE_NAME_2 + " (" + COL_NAME + " TEXT, " + COL_AGE + " TEXT, " + COL_INSTRUCTOR + " TEXT)");
    }

    @Override
    public void onCreate(SQLiteDatabase LoginDb) {
        LoginDb.execSQL("create Table allusers(email TEXT primary key, password TEXT)");
        LoginDb.execSQL("create table userinfo(name TEXT, age TEXT, instructor TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase LoginDb, int i, int i1) {
        LoginDb.execSQL("drop Table if exists allusers");
        LoginDb.execSQL("drop table if exists userinfo");
    }

    public boolean insertData(String email, String password){
        SQLiteDatabase LoginDb = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("password", password);
        long result = LoginDb.insert("allusers",null, contentValues);
        LoginDb.close();

        if (result == -1){
            return false;
        } else {
            return true;
        }
    }

    public boolean insertUserInfo(String name, String age, String instructor){
        SQLiteDatabase userInfoDb = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_NAME, name);
        contentValues.put(COL_AGE, age);
        contentValues.put(COL_INSTRUCTOR, instructor);
        long result = userInfoDb.insert("userinfo", null, contentValues);
        userInfoDb.close();

        if (result == -1){
            return false;
        } else {
            return true;
        }
    }

    public boolean checkEmail(String email){
        SQLiteDatabase LoginDb = this.getWritableDatabase();
        Cursor cursor = LoginDb.rawQuery("Select * from allusers where email = ?", new String[]{email});

        if (cursor.getCount() > 0){
            return true;
        } else {
            return false;
        }
    }
    public boolean checkEmailPassword(String email, String password){
        SQLiteDatabase LoginDb = this.getWritableDatabase();
        Cursor cursor = LoginDb.rawQuery("Select * from allusers where email = ? and password = ?", new String[]{email, password});

        if (cursor.getCount() > 0){
            return true;
        } else {
            return false;
        }
    }
}
