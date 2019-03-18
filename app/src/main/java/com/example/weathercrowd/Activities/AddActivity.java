package com.example.weathercrowd.Activities;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weathercrowd.Misc.GPSTracker;
import com.example.weathercrowd.Misc.WeatherCrowdData;
import com.example.weathercrowd.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class AddActivity extends AppCompatActivity {
    private static final int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    GPSTracker gpsTracker;
    Geocoder geocoder;
    Location location = new Location("");
    WeatherCrowdData weatherCrowdData;
    private DatabaseReference mDatabase;
    EditText editTemperature;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        geocoder = new Geocoder(this, Locale.getDefault());
// Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            // Permission has already been granted
        }
        editTemperature = findViewById(R.id.testedit);
    }

    public void getLocation(View v) {
        TextView locationTextView = findViewById(R.id.locationTextView);
        gpsTracker = new GPSTracker(AddActivity.this);
        if (gpsTracker.canGetLocation()) {
            // Create GPS Object

            location.setLatitude(gpsTracker.getLatitude());
            location.setLongitude(gpsTracker.getLongitude());
            location.setTime(Calendar.getInstance().getTime().getTime());
        } else {
            // GPS is not turned on device
            gpsTracker.showSettingsAlert();
        }
        // Show Position on textView
        try {
            List<Address> addresses = geocoder.getFromLocation(gpsTracker.getLatitude(), gpsTracker.getLongitude(), 1);
            if (addresses != null) {

                locationTextView.setText(addresses.get(0).getAddressLine(0));
            } else {
                locationTextView.setText("Address Not Found");
            }
        } catch (IOException ioException) {
            Toast.makeText(this, "Geocoder error, GPS", Toast.LENGTH_SHORT).show();
        }
    }

    private String getTemperature() {
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
        weatherCrowdData = new WeatherCrowdData(location, Double.parseDouble(getTemperature()));

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child(user.getUid()).child(Calendar.getInstance().getTime().toString()).setValue(weatherCrowdData);

        finish();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request.
        }
    }
}
