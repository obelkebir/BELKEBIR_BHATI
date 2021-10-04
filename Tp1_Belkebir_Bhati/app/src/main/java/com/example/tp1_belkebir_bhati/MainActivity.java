package com.example.tp1_belkebir_bhati;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {
    private Button send_button;
    private TextInputEditText input_message;
    private TextInputEditText input_num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        input_message = (TextInputEditText) findViewById(R.id.input_message);
        input_num = (TextInputEditText) findViewById(R.id.input_num);


    }
    public void sendHandler(View view){
        String message= input_message.getText().toString();
        if (message.length()==0){
            Snackbar.make(view, getString(R.string.error_msg), Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            return;
        }
        String num = input_num.getText().toString();
        String[] numbers= num.split(";");
        System.out.println(numbers[1]);

        for (String number: numbers){
            if (number.length() < 4) {
                Snackbar.make(view, number+' '+getString(R.string.error_num), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
            else
                android.telephony.SmsManager.getDefault().sendTextMessage(number, null, message, null, null);
        }


    }
}