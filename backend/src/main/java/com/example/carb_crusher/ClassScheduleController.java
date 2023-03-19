package com.example.carb_crusher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@RestController


@RequestMapping("/api/class-schedules")
public class ClassScheduleController {

    @Autowired
    private ClassScheduleService classScheduleService;

    @GetMapping
    public List<ClassSchedule> findAll() {
        return classScheduleService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<ClassSchedule> findById(@PathVariable String id) {
        return classScheduleService.findById(id);
    }

    @GetMapping("/gym-location/{gymLocationId}")
    public List<ClassSchedule> findByGymLocationId(@PathVariable String gymLocationId) {
        return classScheduleService.findByGymLocationId(gymLocationId);
    }

    @GetMapping("/gym-location/{gymLocationId}/date-range")
    public List<ClassSchedule> getClassesByGymLocationIdAndDateRange(
            @PathVariable String gymLocationId,
            @RequestParam("startDate") String startDateStr,
            @RequestParam("endDate") String endDateStr) {

        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        LocalDateTime startDate = LocalDateTime.parse(startDateStr + "T00:00:00.000Z", formatter);
        LocalDateTime endDate = LocalDateTime.parse(endDateStr + "T23:59:59.999Z", formatter);

        return classScheduleService.findClassesByGymLocationIdAndDateRange(gymLocationId, startDate, endDate);
    }



    @PostMapping
    public ClassSchedule save(@RequestBody ClassSchedule classSchedule) {
        return classScheduleService.save(classSchedule);
    }

    @PutMapping("/{id}")
    public ClassSchedule update(@PathVariable String id, @RequestBody ClassSchedule updatedClassSchedule) {
        Optional<ClassSchedule> classScheduleOptional = classScheduleService.findById(id);
        if (classScheduleOptional.isPresent()) {
            ClassSchedule classSchedule = classScheduleOptional.get();
            classSchedule.setGymLocationId(updatedClassSchedule.getGymLocationId());
            classSchedule.setClassName(updatedClassSchedule.getClassName());
            classSchedule.setStartTime(updatedClassSchedule.getStartTime());
            classSchedule.setEndTime(updatedClassSchedule.getEndTime());
            classSchedule.setCapacity(updatedClassSchedule.getCapacity());
            return classScheduleService.save(classSchedule);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable String id) {
        classScheduleService.deleteById(id);
    }
}
