package com.example.TP_Belkebir_Bhati;

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
    private static final String DATABASE_NAME = "BookLibrary.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "my_library";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TITLE = "book_title";
    private static final String COLUMN_ISBN = "book_isbn";


    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + TABLE_NAME + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_ISBN + " INTEGER, " +
                COLUMN_TITLE + " TEXT);";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    void addBook(String title, int isbn) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_TITLE, title);
        cv.put(COLUMN_ISBN, isbn);
        long result = db.insert(TABLE_NAME, null, cv);
        if (result == -1) {
            Toast.makeText(mContext, "Failed to insert book", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(mContext, "Book added", Toast.LENGTH_SHORT).show();
        }
    }

    List<String> search(String keywords){
        if (keywords.length() == 0){
            return null;
        }
        // parse keywords to create expression used in query
        String[] parsedKW = keywords.split("\\s+");
        String expr = "%";
        for (String s : parsedKW) {
            expr += s + "%";
        }
        // setup query
        List<String> result = new ArrayList<String>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor;
        cursor = db.rawQuery("SELECT " + COLUMN_TITLE + ", " + COLUMN_ISBN +
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

}
