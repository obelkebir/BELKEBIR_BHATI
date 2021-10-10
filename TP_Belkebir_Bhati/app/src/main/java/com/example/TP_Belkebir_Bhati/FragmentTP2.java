package com.example.TP_Belkebir_Bhati;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import org.mariuszgromada.math.mxparser.*;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;

public class FragmentTP2 extends Fragment {
    private static final String TAG = "FragmentTP1";

    private Button btnNavHome;

    private EditText input;
    // numbers
    private Button btn0;
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private Button btn5;
    private Button btn6;
    private Button btn7;
    private Button btn8;
    private Button btn9;
    // operations
    private Button plus;
    private Button minus;
    private Button mult;
    private Button div;
    // special case
    private Button clear;
    private Button equals;
    private Button dot;
    private ImageButton del;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_tp2,container,false);
        btnNavHome = (Button) view.findViewById(R.id.btnNavHome);

        input = (EditText) view.findViewById(R.id.tp2_input);
        btn0 = (Button) view.findViewById(R.id.tp2_0);
        btn1 = (Button) view.findViewById(R.id.tp2_1);
        btn2 = (Button) view.findViewById(R.id.tp2_2);
        btn3 = (Button) view.findViewById(R.id.tp2_3);
        btn4 = (Button) view.findViewById(R.id.tp2_4);
        btn5 = (Button) view.findViewById(R.id.tp2_5);
        btn6 = (Button) view.findViewById(R.id.tp2_6);
        btn7 = (Button) view.findViewById(R.id.tp2_7);
        btn8 = (Button) view.findViewById(R.id.tp2_8);
        btn9 = (Button) view.findViewById(R.id.tp2_9);
        plus = (Button) view.findViewById(R.id.tp2_plus);
        minus = (Button) view.findViewById(R.id.tp2_minus);
        mult = (Button) view.findViewById(R.id.tp2_mult);
        div = (Button) view.findViewById(R.id.tp2_div);
        clear = (Button) view.findViewById(R.id.tp2_clear);
        equals = (Button) view.findViewById(R.id.tp2_equals);
        dot = (Button) view.findViewById(R.id.tp2_dot);
        del = (ImageButton) view.findViewById(R.id.tp2_delete);

        Log.d(TAG, "onCreateView: started");

        // don't show keyboard on click
        input.setShowSoftInputOnFocus(false);

        btnNavHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Toast.makeText(getActivity(), "Going to Home", Toast.LENGTH_SHORT).show();
                ((MainActivity)getActivity()).setViewPager(0);
            }
        });

        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                addInput("0");
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                addInput("1");
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                addInput("2");
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                addInput("3");
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                addInput("4");
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                addInput("5");
            }
        });

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                addInput("6");
            }
        });

        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                addInput("7");
            }
        });

        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                addInput("8");
            }
        });

        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                addInput("9");
            }
        });

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                addInput("+");
            }
        });

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                addInput("-");
            }
        });

        mult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                addInput("×");
            }
        });

        div.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                addInput("÷");
            }
        });

        dot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                addInput(".");
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                input.setText("");
            }
        });

        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                delInput();
            }
        });

        equals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                String userExp = input.getText().toString();

                userExp.replaceAll("×","*");
                userExp.replaceAll("÷","/");

                Expression exp = new Expression(userExp);

                String result = String.valueOf(exp.calculate());
                input.setText(result);
                input.setSelection(result.length());
            }
        });

        return view;
    }

    // separate into 2 string at cursor position then insert given digit or symbol
    private void addInput(String toAdd){
        String tmp = input.getText().toString();
        int pos = input.getSelectionStart();
        String left = tmp.substring(0,pos);
        String right = tmp.substring(pos);
        input.setText(String.format("%s%s%s",left,toAdd,right));
        input.setSelection(Math.min(tmp.length()+1,pos+1));
    }

    private void delInput(){
        String tmp = input.getText().toString();
        int pos = input.getSelectionStart();
        String left = tmp.substring(0,Math.max(0,pos-1));
        String right = tmp.substring(pos);
        input.setText(String.format("%s%s",left,right));
        input.setSelection(Math.max(0,pos-1));
    }

}
