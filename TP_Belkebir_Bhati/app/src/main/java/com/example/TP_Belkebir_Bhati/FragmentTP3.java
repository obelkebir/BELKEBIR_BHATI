package com.example.TP_Belkebir_Bhati;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;

import java.util.Random;

public class FragmentTP3 extends Fragment {
    private static final String TAG = "FragmentTP3";

    private Button btnNavHome;

    private Button btnAPropos;

    // doors
    private ImageButton door1;
    private ImageButton door2;
    private ImageButton door3;

    private String wikiMontyHall = "https://fr.wikipedia.org/wiki/Probl%C3%A8me_de_Monty_Hall";

    private int correctDoor;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_tp3,container,false);
        btnNavHome = (Button) view.findViewById(R.id.btnNavHome);
        btnAPropos = (Button) view.findViewById(R.id.btnAPropos);
        door1 = (ImageButton) view.findViewById(R.id.tp3_door1);
        door2 = (ImageButton) view.findViewById(R.id.tp3_door2);
        door3 = (ImageButton) view.findViewById(R.id.tp3_door3);
        Log.d(TAG, "onCreateView: started");

        correctDoor = (int) (Math.random() * 2) + 1;

        btnNavHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Toast.makeText(getActivity(), "Going to Home", Toast.LENGTH_SHORT).show();
                ((MainActivity)getActivity()).setViewPager(0);
            }
        });

        btnAPropos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Toast.makeText(getActivity(), "Redirection à propos", Toast.LENGTH_SHORT).show();
                Intent browse = new Intent(Intent.ACTION_VIEW , Uri.parse(wikiMontyHall));

                startActivity(browse);
            }
        });

        door1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getActivity(), MainActivity2.class);
                intent.putExtra("selectedDoor",1);
                intent.putExtra("correctDoor",correctDoor);
                startActivity(intent);
            }
        });

        door2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getActivity(), MainActivity2.class);
                intent.putExtra("selectedDoor",2);
                intent.putExtra("correctDoor",correctDoor);
                startActivity(intent);
            }
        });

        door3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getActivity(), MainActivity2.class);
                intent.putExtra("selectedDoor",3);
                intent.putExtra("correctDoor",correctDoor);
                startActivity(intent);
            }
        });

        return view;
    }

}
