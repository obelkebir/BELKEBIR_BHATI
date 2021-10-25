package com.example.TP_Belkebir_Bhati;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class MainActivity3 extends AppCompatActivity {

    private static final String TAG = "MainActivity3";

    private SectionsStatePagerAdapter mSectionStatePagerAdapter;
    private ViewPager mViewPager;
    private boolean win;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Toast.makeText(MainActivity3.this, "onCreate", Toast.LENGTH_SHORT).show();
        Intent intent = getIntent();
        win = intent.getBooleanExtra("door",false);
        Toast.makeText(MainActivity3.this, "win " + win, Toast.LENGTH_SHORT).show();


        mSectionStatePagerAdapter = new SectionsStatePagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(mViewPager);
        this.setViewPager(0);

    }

    private void setupViewPager(ViewPager viewPager){
        SectionsStatePagerAdapter adapter = new SectionsStatePagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new FragmentTP3EndGame(), "TP 3 End Game");
        viewPager.setAdapter(adapter);
    }

    public void setViewPager(int fragmentNumber){
        mViewPager.setCurrentItem(fragmentNumber);
    }

    public boolean getwin(){
        return win;
    }
}