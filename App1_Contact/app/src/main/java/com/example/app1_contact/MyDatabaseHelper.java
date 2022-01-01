package com.example.app1_contact;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context mContext;
    private static final String DATABASE_NAME = "Contact.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "my_contactsdatabase";
    public static final String COLUMN_ID = "_id";
    public static final String COLUNM_NAME = "contact_name";
    public static final String COLUMN_NUMBER = "contact_number";


    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + TABLE_NAME + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NUMBER + " INTEGER, " +
                COLUNM_NAME + " TEXT);";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    void addContact(String name, int number) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUNM_NAME, name);
        cv.put(COLUMN_NUMBER, number);
        long result = db.insert(TABLE_NAME, null, cv);
        if (result == -1) {
            Toast.makeText(mContext, "Failed to insert contact", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(mContext, "Contact added", Toast.LENGTH_SHORT).show();
        }
    }
/*
    // get total row counts
    // get all rows as list of String
    List<String> getAll(){
        // setup query
        List<String> result = new ArrayList<String>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor;
        cursor = db.rawQuery("SELECT " + COLUMN_TITLE + ", " + COLUMN_RATING +
                        " FROM " + TABLE_NAME +
                        " WHERE " + COLUMN_TITLE + " LIKE ? " +
                        " LIMIT ?",
                new String[]{expr,"1"});
        if (cursor.getCount() == 0) {
            return null;
        } else {
            cursor.moveToFirst();
            result.add(cursor.getString(0));
            result.add(cursor.getString(1));
        }
        return result;
    }
*/

    void clearDB() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE " + TABLE_NAME);
        onCreate(db);
    }

}