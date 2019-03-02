package com.example.weathercrowd;

import java.util.Date;

public class GpsPosition {

    private Double latitude;
    private Double longitude;
    private Date time;

    public GpsPosition(double latitude, double longitude, Date time) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.time = time;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Lat: " + latitude + "Lon: " + longitude + "Time: " + time;
    }

}
