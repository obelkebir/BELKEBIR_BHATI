package com.example.TP_Belkebir_Bhati;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class FragmentTP5 extends Fragment {
    private static final String TAG = "FragmentTP5";

    private Button btnNavHome;

    private TextView count;
    private Button btnStart;
    private Button btnStop;
    private Button btnCount;

    private Boolean started = false;
    private long timer;
    private Object sync = new Object();

    private Button btnGPS;
    private TextView textGPS;
    private Context mContext;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tp5, container, false);
        btnNavHome = (Button) view.findViewById(R.id.btnNavHome);
        count = (TextView) view.findViewById(R.id.textCount);
        btnStart = (Button) view.findViewById(R.id.btnStartService);
        btnStop = (Button) view.findViewById(R.id.btnStopService);
        btnCount = (Button) view.findViewById(R.id.btncount);
        btnGPS = (Button) view.findViewById(R.id.btngps);
        textGPS = (TextView) view.findViewById(R.id.textgps);
        Log.d(TAG, "onCreateView: started");

        btnNavHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Going to Home", Toast.LENGTH_SHORT).show();

                synchronized (sync) {
                    started = false;
                    timer = 0;
                    count.setHint("");
                    count.setText("");
                }
                ((MainActivity) getActivity()).setViewPager(0);
            }
        });

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean launchStart = false;
                synchronized (sync) {
                    if (started) {
                        count.setHint("Already started");
                        count.setText("");
                    } else {
                        count.setHint("");
                        count.setText("");
                        started = true;
                        timer = 0;
                        launchStart = true;
                    }
                }

                if (launchStart) {
                    start();
                }
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                synchronized (sync) {
                    if (started) {
                        count.setHint("");
                        count.setText("");
                        started = false;
                        timer = 0;
                    } else {
                        count.setHint("Already stopped");
                        count.setText("");
                    }
                }
            }
        });

        btnCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                synchronized (sync) {
                    if (started) {
                        count.setHint("");
                        count.setText("" + timer);
                    } else {
                        count.setHint("Service not started");
                        count.setText("");
                    }
                }
            }
        });

        mContext = getContext();

        btnGPS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LocationManager locationManager = (LocationManager)
                        mContext.getSystemService(Context.LOCATION_SERVICE);
                Boolean isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
                if (isGPSEnabled) {

                    LocationListener locationListener = new MyLocationListener();
                    if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        Toast.makeText(getActivity(), "GPS NOT ENABLED, PLEASE GO INTO SETTINGS", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    locationManager.requestLocationUpdates(
                            LocationManager.GPS_PROVIDER, 5000, 10, locationListener);
                } else {
                    Toast.makeText(getActivity(), "GPS NOT ENABLED, PLEASE GO INTO SETTINGS", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }


    private void start() {


        new Thread((new Runnable() {
            @Override
            public void run() {
                timer = 0;
                long current = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
                long last = current;
                while(true){
                    last = current;
                    current = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());

                    synchronized (sync){
                        if (started){
                            timer += (current - last);
                        } else {
                            break;
                        }
                    }
                }
            }
        })).start();

    }

    private class MyLocationListener implements LocationListener {

        @Override
        public void onLocationChanged(Location loc) {
            Toast.makeText(
                    mContext,
                    "Location changed: Lat: " + loc.getLatitude() + " Lng: "
                            + loc.getLongitude(), Toast.LENGTH_SHORT).show();
            String longitude = "Longitude: " + loc.getLongitude();
            Log.v(TAG, longitude);
            String latitude = "Latitude: " + loc.getLatitude();
            Log.v(TAG, latitude);

            /*------- To get city name from coordinates -------- */
            String cityName = null;
            Geocoder gcd = new Geocoder(mContext, Locale.getDefault());
            List<Address> addresses;
            try {
                addresses = gcd.getFromLocation(loc.getLatitude(),
                        loc.getLongitude(), 1);
                if (addresses.size() > 0) {
                    System.out.println(addresses.get(0).getLocality());
                    cityName = addresses.get(0).getLocality();
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            String s = longitude + "\n" + latitude + "\n\nMy Current City is: "
                    + cityName;

            textGPS.setText(s);
            Toast.makeText(mContext, s, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onProviderDisabled(String provider) {}

        @Override
        public void onProviderEnabled(String provider) {}

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {}
    }


}
