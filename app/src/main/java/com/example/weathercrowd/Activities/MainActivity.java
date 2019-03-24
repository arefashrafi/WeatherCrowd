package com.example.weathercrowd.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.weathercrowd.R;

public class MainActivity extends AppCompatActivity {
    // MainActivity for main view when starting application
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void SignInButton(View view) {
        startActivity(new Intent(MainActivity.this, SignInActivity.class));
    }

    public void ViewHeatMap(View view) {
        startActivity(new Intent(MainActivity.this, HeatmapActivity.class));
    }

}