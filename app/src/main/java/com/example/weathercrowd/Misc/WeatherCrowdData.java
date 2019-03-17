package com.example.weathercrowd.Misc;

import android.location.Location;

public class WeatherCrowdData {
    private Location location;
    private Double temperature;

    public WeatherCrowdData(Location location, Double temperature) {
        this.location = location;
        this.temperature = temperature;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

}
