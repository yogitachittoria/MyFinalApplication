package com.cdac.myfinalapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class Studenthelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "Student";
    private static final String DATABASE_NAME = "Finaldb";
    private static final String KEY_NAME = "Name";
    private static final String KEY_FATHERNAME = "Fathername";
    private static final String KEY_MOTHERNAME = "Mothername";
    private static final String KEY_PHONE = "Phone";
    private static final String KEY_ADDRESS = "Address";
    private static final String KEY_BATCH= "Batch";
    private static final String KEY_ID = "id";
    private static final String KEY_LOCATION = "Location";
    private static final String STUDENT_TABLE_CREATE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+ KEY_NAME +" TEXT, " +
                    KEY_FATHERNAME + " TEXT, "+KEY_MOTHERNAME + " TEXT, "+KEY_PHONE + " TEXT, "
        +KEY_ADDRESS + " TEXT, "+KEY_FATHERNAME + " TEXT, "+ KEY_LOCATION + " TEXT );";
    private static final String[] COLUMNS = { KEY_ID,KEY_NAME,KEY_FATHERNAME,KEY_MOTHERNAME ,KEY_PHONE,KEY_ADDRESS,KEY_BATCH,KEY_LOCATION};

    public Studenthelper( Context context) {
        super(context, DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(STUDENT_TABLE_CREATE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(newVersion>oldVersion)
            db.execSQL("DROP TABLE "+ TABLE_NAME);
        onCreate(db);

    }

    void addStudent(String name,String fname,String mname,String phone,String address,String batch,String location)
    {
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues contentValues=new ContentValues();
        contentValues.put(KEY_NAME,name);
        contentValues.put(KEY_FATHERNAME,fname);
        contentValues.put(KEY_MOTHERNAME,mname);
        contentValues.put(KEY_PHONE,phone);
        contentValues.put(KEY_ADDRESS,address);
        contentValues.put(KEY_BATCH,batch);
        contentValues.put(KEY_LOCATION,location);

        db.insert(TABLE_NAME,null,contentValues);
        db.close();

    }
    public boolean getStudentwithemail(String email,String password)
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

        // 4. build Student detail and check for validation
        String pass=(cursor.getString(3));
        if(pass.equals(password))
            return true;
        else
            return false;

    }
   /* public List<Student> getallStudentdetails()
    {
        SQLiteDatabase db=this.getReadableDatabase();
        String query="select * from "+TABLE_NAME;
        Cursor cursor=db.rawQuery(query,null);
        List<Student> list=new ArrayList<Student>();
        if (cursor != null)
        {   // cursor.moveToFirst();

            while(cursor.moveToNext()) {
                // 4. build Student object
                Student p = new Student();
               *//* p.setId(cursor.getInt(0));
                p.setStudentname(cursor.getString(1));
                p.setPassword(cursor.getString(3));
                p.setEmail(cursor.getString(2));
                //add the Student object to list*//*
                list.add(p);
            }}
        return list;
    }
   */
   public Cursor getAllData() {
        String selectQuery = "Select * from "+TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        return cursor;
    }
}
