package com.example.carb_crusher;

public class TimeSpentStats {
    private String date;
    private double hours;

    public TimeSpentStats(String date, double hours) {
        this.date = date;
        this.hours = hours;
    }

    public TimeSpentStats() {
    }

    // Getters and setters

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getHours() {
        return hours;
    }

    public void setHours(double hours) {
        this.hours = hours;
    }
}
