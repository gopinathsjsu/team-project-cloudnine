package com.example.carb_crusher;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "gym_locations")
public class GymLocation {

    @Id
    private String id;

    private String name;
    private String address;
    private String phoneNumber;
    private String openingHours;

    // Constructors, getters, and setters
    public GymLocation() {
    }

    public GymLocation(String name, String address, String phoneNumber, String openingHours) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.openingHours = openingHours;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(String openingHours) {
        this.openingHours = openingHours;
    }

}