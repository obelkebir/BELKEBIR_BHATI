package com.example.TP_Belkebir_Bhati;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class FragmentTP6 extends Fragment {
    private static final String TAG = "FragmentTP6";

    private Button btnNavHome;

    private Context mContext;

    // adding a book
    private EditText addISBN;
    private EditText addTitle;
    private Button btnAdd;

    // searching a book
    private EditText searchKeywords;
    private Button btnsearch;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tp6, container, false);
        btnNavHome = (Button) view.findViewById(R.id.btnNavHome);
        addISBN = (EditText) view.findViewById(R.id.isbn);
        addTitle = (EditText) view.findViewById(R.id.bookTitle);
        btnAdd = (Button) view.findViewById(R.id.btnAddBook);
        searchKeywords = (EditText) view.findViewById(R.id.searchKeywords);
        btnsearch = (Button) view.findViewById(R.id.btnSearchBook);
        Log.d(TAG, "onCreateView: started");

        btnNavHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Going to Home", Toast.LENGTH_SHORT).show();
                ((MainActivity) getActivity()).setViewPager(0);
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(getActivity());
                myDB.addBook(addTitle.getText().toString().trim(), Integer.valueOf(addISBN.getText().toString().trim()));
                addISBN.setText("");
                addTitle.setText("");
            }
        });

        btnsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(getActivity());
                List<String> result = myDB.search(searchKeywords.getText().toString());
                if (result == null) { // not found
                    Toast.makeText(mContext, "No books found", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(getActivity(), MainActivity4.class);
                    intent.putExtra("title", result.get(0));
                    intent.putExtra("isbn", result.get(1));
                    startActivity(intent);
                    searchKeywords.setText("");
                }
            }
        });


        return view;
    }



}
