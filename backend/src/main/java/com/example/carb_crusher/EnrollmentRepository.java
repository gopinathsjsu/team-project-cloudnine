package com.example.carb_crusher;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrollmentRepository extends MongoRepository<Enrollment, String> {
    List<Enrollment> findByUserId(String userId);

    List<Enrollment> findByClassScheduleId(String classScheduleId);

}
