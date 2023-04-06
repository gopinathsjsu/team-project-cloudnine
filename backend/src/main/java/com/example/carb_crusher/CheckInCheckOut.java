package com.example.carb_crusher;

import java.time.LocalDateTime;

public class CheckInCheckOut {
    private LocalDateTime checkInTime;
    private LocalDateTime checkOutTime;

    public CheckInCheckOut() {
    }

    // Getters and setters

    public LocalDateTime getCheckInTime() {
        return checkInTime;
    }

    public void setCheckInTime(LocalDateTime checkInTime) {
        this.checkInTime = checkInTime;
    }

    public LocalDateTime getCheckOutTime() {
        return checkOutTime;
    }

    public void setCheckOutTime(LocalDateTime checkOutTime) {
        this.checkOutTime = checkOutTime;
    }
}
