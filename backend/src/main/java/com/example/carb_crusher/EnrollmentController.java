package com.example.carb_crusher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController


@RequestMapping("/api/enrollments")
public class EnrollmentController {

    @Autowired
    private EnrollmentService enrollmentService;

    @GetMapping("/enroll")
    public Enrollment enrollUserInClass(@RequestParam("userId") String userId, @RequestParam("classScheduleId") String classScheduleId) {
        return enrollmentService.enrollUserInClass(userId, classScheduleId);
    }

    @GetMapping("/user/{userId}/classes")
    public List<ClassSchedule> getEnrolledClassesForUser(@PathVariable("userId") String userId) {
        return enrollmentService.getEnrolledClassesForUser(userId);
    }
    @GetMapping("/enrollmentStats")
    public List<EnrollmentStats> getEnrollmentStats(@RequestParam("period") String period,@RequestParam("locationId") String locationId,
                                                    @RequestParam(value = "classId",required = false) String classId) {
        return enrollmentService.getEnrollmentStats(period,locationId,classId);
    }




}
