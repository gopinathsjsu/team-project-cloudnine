package com.example.carb_crusher;

import java.util.List;

public interface EnrollmentService {
    Enrollment enrollUserInClass(String userId, String classScheduleId);
    List<ClassSchedule> getEnrolledClassesForUser(String userId);
    List<EnrollmentStats> getEnrollmentStats(String period,String locationId,String classId);
}