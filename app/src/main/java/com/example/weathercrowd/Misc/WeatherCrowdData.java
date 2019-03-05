package com.example.weathercrowd.Misc;

public class WeatherCrowdData {
    private GpsPosition gpsPosition;
    private Double temperature;
    private String userId;

    public WeatherCrowdData(GpsPosition gpsPosition, Double temperature, String userId) {
        this.gpsPosition = gpsPosition;
        this.temperature = temperature;
        this.userId = userId;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public GpsPosition getGpsPosition() {
        return gpsPosition;
    }

    public void setGpsPosition(GpsPosition gpsPosition) {
        this.gpsPosition = gpsPosition;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
