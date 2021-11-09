package com.example.TP_Belkebir_Bhati;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentTP4 extends Fragment {
    private static final String TAG = "FragmentTP4";

    private Button btnNavHome;
    private Button btnStart;

    protected ProgressDialog mProgressDialog;


    public static final int MSG_ERR = 0;
    public static final int MSG_CNF = 1;
    public static final int MSG_IND = 2;

    enum ErrorStatus {
        NO_ERROR, ERROR_1, ERROR_2
    };
    private ErrorStatus status;
    private Context mContext;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_tp4,container,false);
        btnNavHome = (Button) view.findViewById(R.id.btnNavHome);
        btnStart = (Button) view.findViewById(R.id.btnStartOps);
        Log.d(TAG, "onCreateView: started");


        btnNavHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Toast.makeText(getActivity(), "Going to Home", Toast.LENGTH_SHORT).show();
                ((MainActivity)getActivity()).setViewPager(0);
            }
        });


        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                start();
            }
        });


        return view;
    }

    private void start() {


        mProgressDialog = ProgressDialog.show(getActivity(), "Please wait",
                "Long operation starts...", true);

        // useful code, variables declarations...
        new Thread((new Runnable() {
            @Override
            public void run() {
                Message msg = null;
                String progressBarData = "Doing long operation 1...";

                // populates the message
                msg = mHandler.obtainMessage(MSG_IND, (Object) progressBarData);

                // sends the message to our handler
                mHandler.sendMessage(msg);

                // starts the first long operation
                status = doLongOperation1();

                if (ErrorStatus.NO_ERROR != status) {
                    Log.e(TAG, "error while parsing the file status:" + status);

                    // error management, creates an error message
                    msg = mHandler.obtainMessage(MSG_ERR,
                            "error while parsing the file status:" + status);
                    // sends the message to our handler
                    mHandler.sendMessage(msg);
                } else {
                    progressBarData = "Doing long operation 2...";
                    mProgressDialog.setMessage(progressBarData);

                    // populates the message
                    msg = mHandler.obtainMessage(MSG_IND,
                            (Object) progressBarData);

                    // sends the message to our handler
                    mHandler.sendMessage(msg);

                    // starts the second long operation
                    status = doLongOperation2();

                    if (ErrorStatus.NO_ERROR != status) {
                        Log.e(TAG, "error while computing the path status:"
                                + status);
                        // error management,creates an error message
                        msg = mHandler.obtainMessage(MSG_ERR,
                                "error while computing the path status:"
                                        + status);
                        // sends the message to our handler
                        mHandler.sendMessage(msg);
                    } else {
                        msg = mHandler.obtainMessage(MSG_CNF,
                                "Parsing and computing ended successfully !");
                        // sends the message to our handler
                        mHandler.sendMessage(msg);
                    }
                }
            }
        })).start();
    }

    /** fake operation for testing purpose */
    protected ErrorStatus doLongOperation1() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        return ErrorStatus.NO_ERROR;
    }

    /** fake operation for testing purpose */
    protected ErrorStatus doLongOperation2() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
        }
        return ErrorStatus.NO_ERROR;
    }

    final Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            String text2display = null;
            switch (msg.what) {
                case MSG_IND:
                    if (mProgressDialog.isShowing()) {
                        mProgressDialog.setMessage(((String) msg.obj));
                    }
                    break;
                case MSG_ERR:
                    text2display = (String) msg.obj;
                    Toast.makeText(mContext, "Error: " + text2display,
                            Toast.LENGTH_LONG).show();
                    if (mProgressDialog.isShowing()) {
                        mProgressDialog.dismiss();
                    }
                    break;
                case MSG_CNF:
                    text2display = (String) msg.obj;
                    /*
                    Toast.makeText(mContext, "Info: " + text2display,
                            Toast.LENGTH_LONG).show();
                     */
                    if (mProgressDialog.isShowing()) {
                        mProgressDialog.dismiss();
                    }
                    break;
                default: // should never happen
                    break;
            }
        }
    };


}
