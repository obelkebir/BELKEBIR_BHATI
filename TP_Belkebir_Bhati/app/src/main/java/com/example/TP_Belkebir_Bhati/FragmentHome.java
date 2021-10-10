package com.example.TP_Belkebir_Bhati;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentHome extends Fragment {
    private static final String TAG = "FragmentHome";

    private Button btnNavTP1;
    private Button btnNavTP2;
    private Button btnNavTP3;
    private Button btnNavTP4;
    private Button btnNavTP5;
    private Button btnNavTP6;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_home,container,false);
        btnNavTP1 = (Button) view.findViewById(R.id.btnNavTP1);
        btnNavTP2 = (Button) view.findViewById(R.id.btnNavTP2);
        btnNavTP3 = (Button) view.findViewById(R.id.btnNavTP3);
        btnNavTP4 = (Button) view.findViewById(R.id.btnNavTP4);
        btnNavTP5 = (Button) view.findViewById(R.id.btnNavTP5);
        btnNavTP6 = (Button) view.findViewById(R.id.btnNavTP6);
        Log.d(TAG, "onCreateView: started");

        btnNavTP1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Toast.makeText(getActivity(), "Going to TP 1", Toast.LENGTH_SHORT).show();
                ((MainActivity)getActivity()).setViewPager(1);
            }
        });

        btnNavTP2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Toast.makeText(getActivity(), "Going to TP 2", Toast.LENGTH_SHORT).show();
                ((MainActivity)getActivity()).setViewPager(2);
            }
        });

        btnNavTP3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Toast.makeText(getActivity(), "Going to TP 3", Toast.LENGTH_SHORT).show();
                ((MainActivity)getActivity()).setViewPager(3);
            }
        });

        btnNavTP4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Toast.makeText(getActivity(), "Going to TP 4", Toast.LENGTH_SHORT).show();
                ((MainActivity)getActivity()).setViewPager(4);
            }
        });

        btnNavTP5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Toast.makeText(getActivity(), "Going to TP 5", Toast.LENGTH_SHORT).show();
                ((MainActivity)getActivity()).setViewPager(5);
            }
        });

        btnNavTP6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Toast.makeText(getActivity(), "Going to TP 6", Toast.LENGTH_SHORT).show();
                ((MainActivity)getActivity()).setViewPager(6);
            }
        });



        return view;
    }

}
