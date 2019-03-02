package com.example.weathercrowd;

public class WeatherCrowdData {
    private GpsPosition gpsPosition;
    private String temperature;

    public WeatherCrowdData(GpsPosition gpsPosition, String temperature) {
        this.gpsPosition = gpsPosition;
        this.temperature = temperature;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public GpsPosition getGpsPosition() {
        return gpsPosition;
    }

    public void setGpsPosition(GpsPosition gpsPosition) {
        this.gpsPosition = gpsPosition;
    }
}
