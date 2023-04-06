package com.example.carb_crusher;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.time.LocalDateTime;

public interface CheckInRepository extends MongoRepository<CheckIn, String> {
    long countByCheckInTimeBetweenAndGymLocationId(LocalDateTime start, LocalDateTime end, String locationId);
}


