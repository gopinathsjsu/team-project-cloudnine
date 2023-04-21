package com.example.carb_crusher;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(collection = "memberships")
public class Membership {
    @Id
    private String id;
    private String name;
    private BigDecimal price;
    private String duration; // Duration can be in days, weeks or months

    public Membership() {
    }

    public Membership(String name, BigDecimal price, String duration) {
        this.name = name;
        this.price = price;
        this.duration = duration;
    }

    // Getters and setters

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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
