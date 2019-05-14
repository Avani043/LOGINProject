package com.example.acer.login;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBhelper extends SQLiteOpenHelper {

    public static String DB_name="Databse.db";
    public static String Table="Reg";
    public static String Email="Email";
    public static String Name="Username";
    public static String Password="Password";
    public DBhelper(Context context) {
        super(context, DB_name,null, 1);
    }
    SQLiteDatabase db;
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+Table+"(Email TEXT PRIMARY KEY,Username TEXT,Password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop Table If Exists "+Table);
        onCreate(db);
    }
    public boolean insert(String Email,String Username,String Password){
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Email",Email);
        values.put("Username",Username);
        values.put("Password",Password);
        long result=db.insert(Table,null,values);
        db.close();
        if(result==-1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    public String searchpassword(String username)
    {
        db = this.getReadableDatabase();
        String query = "select Username,Password from " +Table;
        Cursor cursor =db.rawQuery(query , null);
        String a,b;
        b = "Not found";
        if(cursor.moveToFirst())
        {
            do{
                a= cursor.getString(0);
                if(a.equals(username))
                {
                    b= cursor.getString(1);
                    break;
                }
            }while(cursor.moveToNext());
        }
        return b;
    }
}
