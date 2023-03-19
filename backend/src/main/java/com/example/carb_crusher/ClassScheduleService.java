package com.example.carb_crusher;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ClassScheduleService {
    List<ClassSchedule> findAll();
    Optional<ClassSchedule> findById(String id);
    List<ClassSchedule> findByGymLocationId(String gymLocationId);

    List<ClassSchedule> findClassesByGymLocationIdAndDateRange(String gymLocationId, LocalDateTime startDate, LocalDateTime endDate);
    ClassSchedule save(ClassSchedule classSchedule);
    void deleteById(String id);
}
