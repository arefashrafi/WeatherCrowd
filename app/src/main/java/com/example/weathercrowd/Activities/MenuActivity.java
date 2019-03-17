package com.example.weathercrowd.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.weathercrowd.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MenuActivity extends AppCompatActivity {
    FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void GoToAddActivity(View view) {
        startActivity(new Intent(MenuActivity.this, AddActivity.class));
    }

    public void GoToHeatmapActivity(View view) {
        startActivity(new Intent(MenuActivity.this, HeatmapActivity.class));
    }

    public void GoToUserInfoActivity(View view) {
        startActivity(new Intent(MenuActivity.this, UserInfoActivity.class));
    }

    public void GoToUserHistoryActivity(View view) {
        startActivity(new Intent(MenuActivity.this, HistoryActivity.class));
    }
}
