package com.example.carb_crusher;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
public interface ClassScheduleRepository extends MongoRepository<ClassSchedule, String> {
    List<ClassSchedule> findByGymLocationId(String gymLocationId);

    List<ClassSchedule> findByGymLocationIdAndStartTimeBetween(String gymLocationId, LocalDateTime start, LocalDateTime end);
}
