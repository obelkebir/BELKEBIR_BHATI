package com.example.TP_Belkebir_Bhati;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.TP_Belkebir_Bhati.R;
import com.google.android.material.snackbar.Snackbar;

public class FragmentTP1 extends Fragment {
    private static final String TAG = "FragmentTP1";

    private Button btnNavHome;

    // simple text display in toast
    private Button btnSend;
    private EditText editText;

    // sms sender
    private Button btnSendSMS;
    private EditText number;
    private EditText message;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_tp1,container,false);
        btnNavHome = (Button) view.findViewById(R.id.btnNavHome);
        btnSend = (Button) view.findViewById(R.id.tp1SendBtn);
        editText = (EditText) view.findViewById(R.id.tp1EditText);
        btnSendSMS = (Button) view.findViewById(R.id.tp1sendMsgBtn);
        number = (EditText) view.findViewById(R.id.tp1numero);
        message = (EditText) view.findViewById(R.id.tp1message);
        Log.d(TAG, "onCreateView: started");

        btnNavHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Toast.makeText(getActivity(), "Going to Home", Toast.LENGTH_SHORT).show();
                ((com.example.TP_Belkebir_Bhati.MainActivity)getActivity()).setViewPager(0);
            }
        });

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                String msg = editText.getText().toString();
                editText.setText("");
                Toast.makeText(getActivity(), "Received : " + msg, Toast.LENGTH_SHORT).show();
            }
        });

        btnSendSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                String msg = message.getText().toString();
                if (msg.length()==0){
                    Snackbar.make(view, getString(R.string.error_msg), Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    return;
                }
                String num = number.getText().toString();
                String[] numbers= num.split(";");
                //System.out.println(numbers[1]);

                for (String number: numbers){
                    if (number.length() < 4) {
                        Snackbar.make(view, number+' '+getString(R.string.error_num), Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();

                    }
                    else
                        android.telephony.SmsManager.getDefault().sendTextMessage(number, null, msg, null, null);
                }
                Toast.makeText(getActivity(), "Message sent", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

}
