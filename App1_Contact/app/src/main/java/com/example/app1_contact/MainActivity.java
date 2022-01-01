package com.example.app1_contact;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Loader;
import android.os.Bundle;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
/*
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
*/
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.CursorLoader;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{

    private static final int RESULT_PICK_CONTACT =1;
    private TextView phone;
    private Button select;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);

        phone = findViewById (R.id.phone);
        select = findViewById (R.id.select);

        select.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Intent in = new Intent (Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
                startActivityForResult (in, RESULT_PICK_CONTACT);
            }
        });

        getLoaderManager().initLoader(0, null, this);
        deleteAllContacts();
        insertAllContacts();
        restartLoader();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,  Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK)
        {
            switch (requestCode) {
                case RESULT_PICK_CONTACT:
                    contactPicked (data);
                    break;
            }
        }
        else
        {
            Toast.makeText (this, "Failed To pick contact", Toast.LENGTH_SHORT).show ();
        }
    }

    private void contactPicked(Intent data) {
        Cursor cursor = null;

        try {
            String phoneNo = null;
            Uri uri = data.getData ();
            cursor = getContentResolver ().query (uri, null, null,null,null);
            cursor.moveToFirst ();
            int phoneIndex = cursor.getColumnIndex (ContactsContract.CommonDataKinds.Phone.NUMBER);

            phoneNo = cursor.getString (phoneIndex);

            phone.setText (phoneNo);


        } catch (Exception e) {
            e.printStackTrace ();
        }
    }

    private void insertAllContacts() {
        Cursor cursor = null;
        int totalContacts = 0;
        try {
            int phoneNo = 0;
            String phoneName = "";
            cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,null,null, null);
            while (cursor.moveToNext()) {
                totalContacts++;

                int phoneIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                int nameIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);

                phoneNo = new Integer(cursor.getString(phoneIndex));
                phoneName = cursor.getString(nameIndex);

                ContentValues values = new ContentValues();
                values.put(MyDatabaseHelper.COLUNM_NAME, phoneName);
                values.put(MyDatabaseHelper.COLUMN_NUMBER, phoneNo);
                Uri contactUri = getContentResolver().insert(ContactsProvider.CONTENT_URI, values);
            }

        } catch (Exception e) {
            e.printStackTrace ();
        }
        cursor.close();
        Toast.makeText(this, "Created All Contact : " + totalContacts, Toast.LENGTH_LONG).show();
    }

    private void deleteAllContacts() {

        getContentResolver().delete(ContactsProvider.CONTENT_URI, null, null);
        restartLoader();
        Toast.makeText(this, "All Contacts Deleted", Toast.LENGTH_LONG).show();
    }

    private void restartLoader() {
        getLoaderManager().restartLoader(0, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(this, ContactsProvider.CONTENT_URI, null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}