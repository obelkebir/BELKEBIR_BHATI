package com.example.TP_Belkebir_Bhati;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentTP3EndGame extends Fragment {
    private static final String TAG = "FragmentTP3";

    private Button btnNavHome;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_tp3_endgame,container,false);
        btnNavHome = (Button) view.findViewById(R.id.btnNavHome);
        TextView result = (TextView) view.findViewById(R.id.result);
        if (((MainActivity3)getActivity()).getwin()) {
            result.setText("CONGRATULATION YOU WON !!!");
        } else {
           result.setText("TOO BAD, SO SAD, YOU LOST :( ");
        }
        Log.d(TAG, "onCreateView: started");

        btnNavHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });


        return view;
    }

}
