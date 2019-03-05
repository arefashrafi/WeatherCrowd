package com.example.weathercrowd.Activities;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weathercrowd.Misc.GPSTracker;
import com.example.weathercrowd.Misc.GpsPosition;
import com.example.weathercrowd.Misc.WeatherCrowdData;
import com.example.weathercrowd.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class AddActivity extends AppCompatActivity {
    GPSTracker gpsTracker;
    WeatherCrowdData weatherCrowdData;
    GpsPosition gpsPosition;
    private DatabaseReference mDatabase;

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

    public void submitButtonPressed(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle("Confirm");
        builder.setMessage("Are you sure the data is correct?");
        builder.setPositiveButton("Confirm",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sendToDatabase();
                    }
                });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void sendToDatabase() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        // Send to DB
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child(user.getUid()).child("temperature").setValue(41.1);
        mDatabase.child(user.getUid()).child("location").setValue(gpsPosition);
        mDatabase.child(user.getUid()).child("date").setValue(gpsPosition.getTime());
        Toast.makeText(this, "Data sent", Toast.LENGTH_SHORT).show();
    }
}
