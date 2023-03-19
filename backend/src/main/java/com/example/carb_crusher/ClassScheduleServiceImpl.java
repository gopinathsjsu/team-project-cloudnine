package com.example.carb_crusher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ClassScheduleServiceImpl implements ClassScheduleService {

    @Autowired
    private ClassScheduleRepository classScheduleRepository;

    @Override
    public List<ClassSchedule> findAll() {
        return classScheduleRepository.findAll();
    }

    @Override
    public Optional<ClassSchedule> findById(String id) {
        return classScheduleRepository.findById(id);
    }

    @Override
    public List<ClassSchedule> findByGymLocationId(String gymLocationId) {
        return classScheduleRepository.findByGymLocationId(gymLocationId);
    }
    @Override
    public List<ClassSchedule> findClassesByGymLocationIdAndDateRange(String gymLocationId, LocalDateTime startDate, LocalDateTime endDate) {
        return classScheduleRepository.findByGymLocationIdAndStartTimeBetween(gymLocationId, startDate, endDate);
    }

    @Override
    public ClassSchedule save(ClassSchedule classSchedule) {
        return classScheduleRepository.save(classSchedule);
    }

    @Override
    public void deleteById(String id) {
        classScheduleRepository.deleteById(id);
    }
}
