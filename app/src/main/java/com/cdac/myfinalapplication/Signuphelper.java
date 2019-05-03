package com.cdac.myfinalapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class Signuphelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "User";
    private static final String DATABASE_NAME = "Finaldb";
    private static final String KEY_USERNAME = "UserName";
    private static final String KEY_EMAIL = "Email";
    private static final String KEY_PASSWORD= "Password";
    private static final String KEY_ID = "id";
    private static final String USER_TABLE_CREATE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+ KEY_USERNAME +" TEXT, " +
                    KEY_EMAIL + " TEXT, "+KEY_PASSWORD + " TEXT);";
    private static final String[] COLUMNS = { KEY_ID,KEY_USERNAME,KEY_EMAIL,KEY_PASSWORD };

    public Signuphelper( Context context) {
        super(context, DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(USER_TABLE_CREATE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(newVersion>oldVersion)
            db.execSQL("DROP TABLE "+ TABLE_NAME);
        onCreate(db);

    }

    void addUser(String name,String email,String password)
    {
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues contentValues=new ContentValues();
        contentValues.put(KEY_USERNAME,name);
        contentValues.put(KEY_EMAIL,email);
        contentValues.put(KEY_PASSWORD,password);
        db.insert(TABLE_NAME,null,contentValues);
        db.close();

    }
    public boolean getUserwithemail(String email,String password)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.query(TABLE_NAME, // a. table
                COLUMNS, // b. column names
                " Email = ?", // c. selections
                new String[] { email }, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit

        // 3. if we got results get the first one
        if (cursor != null)
            cursor.moveToFirst();

        // 4. build User detail and check for validation
        String pass=(cursor.getString(3));
        if(pass.equals(password))
            return true;
        else
            return false;

    }
    public List<User> getallUserdetails()
    {
        SQLiteDatabase db=this.getReadableDatabase();
        String query="select * from "+TABLE_NAME;
        Cursor cursor=db.rawQuery(query,null);
        List<User> list=new ArrayList<User>();
        if (cursor != null)
        {   // cursor.moveToFirst();

            while(cursor.moveToNext()) {
                // 4. build User object
                User p = new User();
                p.setId(cursor.getInt(0));
                p.setUsername(cursor.getString(1));
                p.setPassword(cursor.getString(3));
                p.setEmail(cursor.getString(2));
                //add the User object to list
                list.add(p);
            }}
        return list;
    }
    }



