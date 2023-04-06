package com.example.carb_crusher;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Document(collection = "checkIns")
public class CheckIn {

    @Id
    private ObjectId id;

    private String gymLocationId;

    private LocalDateTime checkInTime;

    private LocalTime checkInHour;


    // Getters and Setters

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public void setCheckInHour(LocalTime checkInHour) {
        this.checkInHour = checkInHour;
    }
    public LocalTime getCheckInHour() {
        return this.checkInHour;
    }
    public String getGymLocationId() {
        return gymLocationId;
    }

    public void setGymLocationId(String gymLocationId) {
        this.gymLocationId = gymLocationId;
    }

    public LocalDateTime getCheckInTime() {
        return checkInTime;
    }

    public void setCheckInTime(LocalDateTime checkInTime) {
        this.checkInTime = checkInTime;
    }
}
