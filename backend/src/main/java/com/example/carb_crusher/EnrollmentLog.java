package com.example.carb_crusher;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "enrollment_log")
public class EnrollmentLog {

    @Id
    private String id;

    private String gymLocationId;
    private String classId;
    private String userId;
    private LocalDateTime enrollmentDate;

    public EnrollmentLog() {
    }

    public EnrollmentLog(String gymLocationId, String classId, String userId, LocalDateTime enrollmentDate) {
        this.gymLocationId = gymLocationId;
        this.classId = classId;
        this.userId = userId;
        this.enrollmentDate = enrollmentDate;
    }

    // Getter and setter for id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    // Getter and setter for gymLocationId
    public String getGymLocationId() {
        return gymLocationId;
    }

    public void setGymLocationId(String gymLocationId) {
        this.gymLocationId = gymLocationId;
    }

    // Getter and setter for classId
    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    // Getter and setter for userId
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    // Getter and setter for enrollmentDate
    public LocalDateTime getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(LocalDateTime enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }
}
