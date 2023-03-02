package com.example.carb_crusher;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Document(collection = "users")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class User {

    @Id
    private String id;

    private String gymLocationId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private LocalDateTime lastCheckIn;
    private LocalDateTime lastCheckOut;
    private List<CheckInCheckOut> checkInCheckOutList;
    private LocalDateTime membershipStartDate;
    private boolean isAdmin;

    @DBRef
    private Membership membership;

    @DBRef
    private List<ClassSchedule> enrolledClasses;

    @DBRef
    private List<Activity> activities;

    public User() {
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public LocalDateTime getLastCheckIn() {
        return lastCheckIn;
    }

    public void setLastCheckIn(LocalDateTime lastCheckIn) {
        this.lastCheckIn = lastCheckIn;
    }

    public LocalDateTime getLastCheckOut() {
        return lastCheckOut;
    }

    public void setLastCheckOut(LocalDateTime lastCheckOut) {
        this.lastCheckOut = lastCheckOut;
    }

    public List<ClassSchedule> getEnrolledClasses() {
        return enrolledClasses;
    }

    public void setEnrolledClasses(List<ClassSchedule> enrolledClasses) {
        this.enrolledClasses = enrolledClasses;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }

    public List<CheckInCheckOut> getCheckInCheckOutList() {
        if (checkInCheckOutList == null) {
            checkInCheckOutList = new ArrayList<>();
        }
        return checkInCheckOutList;
    }
    public void setCheckInCheckOutList(List<CheckInCheckOut> checkInCheckOutList) {
        this.checkInCheckOutList = checkInCheckOutList;
    }

    public LocalDateTime getMembershipStartDate() {
        return membershipStartDate;
    }

    public void setMembershipStartDate(LocalDateTime membershipStartDate) {
        this.membershipStartDate = membershipStartDate;
    }

    public Membership getMembership() {
        return membership;
    }

    public void setMembership(Membership membership) {
        this.membership = membership;
    }
}
