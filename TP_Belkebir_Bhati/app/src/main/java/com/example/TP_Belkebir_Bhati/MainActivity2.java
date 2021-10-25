package com.example.TP_Belkebir_Bhati;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.TP_Belkebir_Bhati.R;

public class MainActivity2 extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private SectionsStatePagerAdapter mSectionStatePagerAdapter;
    private ViewPager mViewPager;
    private int selectedDoor;
    private int correctDoor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toast.makeText(MainActivity2.this, "onCreate", Toast.LENGTH_SHORT).show();
        Intent intent = getIntent();
        selectedDoor = intent.getIntExtra("selectedDoor",1);
        correctDoor = intent.getIntExtra("correctDoor",1);
        Toast.makeText(MainActivity2.this, "correctDoor " + correctDoor + "selectedDoor " + selectedDoor, Toast.LENGTH_SHORT).show();

        mSectionStatePagerAdapter = new SectionsStatePagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(mViewPager);
        this.setViewPager(0); // send to confirm fragment

    }

    private void setupViewPager(ViewPager viewPager){
        SectionsStatePagerAdapter adapter = new SectionsStatePagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new FragmentTP3Confirm(), "TP 3 Confirm");
        adapter.addFragment(new FragmentTP3SecondChoice(), "TP 3 Second Choice");
        viewPager.setAdapter(adapter);
    }

    public void setViewPager(int fragmentNumber){
        mViewPager.setCurrentItem(fragmentNumber);
    }

    public int getSelectedDoor(){
        return selectedDoor;
    }

    public int getCorrectDoor(){
        return correctDoor;
    }
}