package com.example.weathercrowd.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.weathercrowd.R;

public class EditHistoryActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_history);
    }

    public void EditEntry() {
        // Get Intent data, find the entry in the database and then show a view that collects new data and update then send to database
    }

    public void DeleteEntry() {
        // Get intent data, delete entry ID from the database and the finish(); i.e go back to list
    }
}
