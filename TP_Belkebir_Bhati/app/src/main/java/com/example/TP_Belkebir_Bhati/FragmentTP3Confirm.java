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

public class FragmentTP3Confirm extends Fragment {
    private static final String TAG = "FragmentTP3";

    private Button btnAPropos;

    // doors+
    private Button btnChoix1;
    private Button btnChoix2;

    private String wikiMontyHall = "https://fr.wikipedia.org/wiki/Probl%C3%A8me_de_Monty_Hall";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_tp3_confirm,container,false);
        btnAPropos = (Button) view.findViewById(R.id.btnAPropos);
        btnChoix1 = (Button) view.findViewById(R.id.tp3_choix1);
        btnChoix2 = (Button) view.findViewById(R.id.tp3_choix2);
        Log.d(TAG, "onCreateView: started");

        btnAPropos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Toast.makeText(getActivity(), "Redirection à propos", Toast.LENGTH_SHORT).show();
                Intent browse = new Intent(Intent.ACTION_VIEW , Uri.parse(wikiMontyHall));

                startActivity(browse);
            }
        });

        btnChoix1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getActivity(), MainActivity3.class);
                intent.putExtra("win",
                        ((MainActivity2)getActivity()).getSelectedDoor() == ((MainActivity2)getActivity()).getCorrectDoor());
                startActivity(intent);
            }
        });

        btnChoix2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                ((MainActivity2)getActivity()).setViewPager(1);
            }
        });

        return view;
    }

}
