package com.example.carb_crusher;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface EnrollmentLogRepository extends MongoRepository<EnrollmentLog, String> {
    List<EnrollmentLog> findByEnrollmentDateBetween(LocalDateTime startDate, LocalDateTime endDate);
}