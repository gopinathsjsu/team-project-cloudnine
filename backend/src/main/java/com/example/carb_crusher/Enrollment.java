package com.example.carb_crusher;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "enrollments")
public class Enrollment {

    @Id
    private String id;

    @DBRef
    private User user;

    @DBRef
    private ClassSchedule classSchedule;

    public Enrollment() {
    }

    public Enrollment(User user, ClassSchedule classSchedule) {
        this.user = user;
        this.classSchedule = classSchedule;
    }

    // Getters and setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ClassSchedule getClassSchedule() {
        return classSchedule;
    }

    public void setClassSchedule(ClassSchedule classSchedule) {
        this.classSchedule = classSchedule;
    }
}
