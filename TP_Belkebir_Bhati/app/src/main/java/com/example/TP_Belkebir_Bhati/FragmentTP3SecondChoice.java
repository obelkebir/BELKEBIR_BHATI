package com.example.TP_Belkebir_Bhati;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentTP3SecondChoice extends Fragment {
    private static final String TAG = "FragmentTP3";

    private Button btnAPropos;

    // doors
    private ImageButton door1;
    private ImageButton door2;

    private String wikiMontyHall = "https://fr.wikipedia.org/wiki/Probl%C3%A8me_de_Monty_Hall";

    private int leftDoor;
    private int rightDoor;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_tp3_second_choice,container,false);
        btnAPropos = (Button) view.findViewById(R.id.btnAPropos);
        door1 = (ImageButton) view.findViewById(R.id.tp3_door1);
        door2 = (ImageButton) view.findViewById(R.id.tp3_door2);
        Log.d(TAG, "onCreateView: started");

        btnAPropos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Toast.makeText(getActivity(), "Redirection à propos", Toast.LENGTH_SHORT).show();
                Intent browse = new Intent(Intent.ACTION_VIEW , Uri.parse(wikiMontyHall));

                startActivity(browse);
            }
        });

        // on supprime la mauvaise porte la plus à gauche
        int deletedDoor = ((MainActivity2)getActivity()).getCorrectDoor() == 1 ? 2 : 1;
        leftDoor = deletedDoor == 1 ? 2 : 1;
        rightDoor = deletedDoor == 2 ? 3 : 2;

        door1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getActivity(), MainActivity3.class);
                intent.putExtra("win",
                        leftDoor == ((MainActivity2)getActivity()).getCorrectDoor());
                startActivity(intent);
            }
        });

        door2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getActivity(), MainActivity3.class);
                intent.putExtra("win",
                        rightDoor == ((MainActivity2)getActivity()).getCorrectDoor());
                startActivity(intent);
            }
        });


        return view;
    }

}
