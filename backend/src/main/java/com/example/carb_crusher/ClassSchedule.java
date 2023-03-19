package com.example.carb_crusher;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Document(collection = "class_schedules")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class ClassSchedule {
    @Id
    private String id;
    private String gymLocationId;
    private String className;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int capacity;

    @DBRef
    private List<User> enrolledUsers;

    public ClassSchedule() {
    }

    public ClassSchedule(String gymLocationId, String className, LocalDateTime startTime, LocalDateTime endTime, int capacity) {
        this.gymLocationId = gymLocationId;
        this.className = className;
        this.startTime = startTime;
        this.endTime = endTime;
        this.capacity = capacity;
    }

    // Getters and setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGymLocationId() {
        return gymLocationId;
    }

    public void setGymLocationId(String gymLocationId) {
        this.gymLocationId = gymLocationId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
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

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public List<User> getEnrolledUsers() {
        return enrolledUsers;
    }

    public void setEnrolledUsers(List<User> enrolledUsers) {
        this.enrolledUsers = enrolledUsers;
    }


}
