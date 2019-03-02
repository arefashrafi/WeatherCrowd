package com.example.weathercrowd;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

public class AddActivity extends AppCompatActivity {
    GPSTracker gpsTracker;
    WeatherCrowdData weatherCrowdData;
    GpsPosition gpsPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
    }

    public void getLocation(View v) {
        gpsTracker = new GPSTracker(AddActivity.this);
        if (gpsTracker.canGetLocation()) {
            // Create GPS Object
            gpsPosition = new GpsPosition(gpsTracker.getLatitude(), gpsTracker.getLongitude(), Calendar.getInstance().getTime());
        } else {
            // GPS is not turned on device
            gpsTracker.showSettingsAlert();
        }
        // Show Position on textView
        TextView locationTextView = findViewById(R.id.locationTextView);
        locationTextView.setText(gpsPosition.toString());

    }

    private String getTemperature() {
        EditText editTemperature = findViewById(R.id.temperatureInput);
        return editTemperature.getText().toString();
    }

    private void submit_data() {
        // Get Temp and connect it to GPS Pos
        weatherCrowdData = new WeatherCrowdData(gpsPosition, getTemperature());
        // Then to DB

    }
}
