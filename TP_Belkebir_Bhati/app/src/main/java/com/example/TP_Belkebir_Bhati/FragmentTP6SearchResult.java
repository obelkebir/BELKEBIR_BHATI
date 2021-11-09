package com.example.TP_Belkebir_Bhati;

import android.content.Context;
import android.content.Intent;
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
import androidx.fragment.app.Fragment;

public class FragmentTP6SearchResult extends Fragment {
    private static final String TAG = "FragmentTP6";

    private Button btnNavHome;

    private Context mContext;

    private TextView title;
    private TextView isbn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tp6searchresult, container, false);
        btnNavHome = (Button) view.findViewById(R.id.btnNavHome);
        title = (TextView) view.findViewById(R.id.bookTitle);
        isbn = (TextView) view.findViewById(R.id.bookISBN);
        Log.d(TAG, "onCreateView: started");

        btnNavHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });

        title.setText(((MainActivity4)getActivity()).title);
        isbn.setText(((MainActivity4)getActivity()).isbn);


        return view;
    }



}
