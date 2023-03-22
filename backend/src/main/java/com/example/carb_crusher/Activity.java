package com.example.carb_crusher;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Document(collection = "activities")
public class Activity {
    @Id
    private String id;
    private String userId; // Renamed from memberId
    private String gymLocationId;
    private String activityType;
    private LocalDate loggedDate;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public Activity() {
    }

    public Activity(String userId, String gymLocationId, String activityType, LocalDateTime startTime, LocalDateTime endTime) {
        this.userId = userId;
        this.gymLocationId = gymLocationId;
        this.activityType = activityType;
        this.startTime = startTime;
        this.endTime = endTime;
        this.loggedDate = LocalDate.now();
    }


    // Getters and setters (Update the getter and setter for userId)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    public String getGymLocationId() {
        return gymLocationId;
    }

    public void setGymLocationId(String gymLocationId) {
        this.gymLocationId = gymLocationId;
    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public LocalDate getLoggedDate() {
        return loggedDate;
    }

    public void setLoggedDate(LocalDate loggedDate) {
        this.loggedDate = loggedDate;
    }

}
