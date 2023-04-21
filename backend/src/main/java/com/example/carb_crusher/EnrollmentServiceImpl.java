package com.example.carb_crusher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Service;

import org.springframework.data.mongodb.core.query.Criteria;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EnrollmentLogRepository enrollmentLogRepository;


    @Autowired
    private ClassScheduleRepository classScheduleRepository;

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Override
    public Enrollment enrollUserInClass(String userId, String classScheduleId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));

        ClassSchedule classSchedule = classScheduleRepository.findById(classScheduleId)
                .orElseThrow(() -> new IllegalArgumentException("Class schedule not found with id: " + classScheduleId));

        // Update the user's enrolledClasses list
        List<ClassSchedule> enrolledClasses = user.getEnrolledClasses();
        if (enrolledClasses == null) {
            enrolledClasses = new ArrayList<>();
            user.setEnrolledClasses(enrolledClasses);
        }
        enrolledClasses.add(classSchedule);

        // Save the updated user object
        userRepository.save(user);

        // Update the classSchedule's enrolledUsers list
        List<User> enrolledUsers = classSchedule.getEnrolledUsers();
        if (enrolledUsers == null) {
            enrolledUsers = new ArrayList<>();
            classSchedule.setEnrolledUsers(enrolledUsers);
        }
        enrolledUsers.add(user);

        // Save the updated classSchedule object
        classScheduleRepository.save(classSchedule);

        System.out.println("User: " + user.toString());
        System.out.println("ClassSchedule: " + classSchedule.toString());

        EnrollmentLog enrollmentLog = new EnrollmentLog(
                classSchedule.getGymLocationId(),
                classSchedule.getId(),
                user.getId(),
                LocalDateTime.now()
        );
        enrollmentLogRepository.save(enrollmentLog);
        Enrollment enrollment = new Enrollment(user, classSchedule);
        return enrollmentRepository.save(enrollment);
    }


    @Override
    public List<ClassSchedule> getEnrolledClassesForUser(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));

        return user.getEnrolledClasses();
    }
    @Override
    public List<EnrollmentStats> getEnrollmentStats(String period,String locationId,String classId) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime startDate;
        LocalDateTime endDate = now;

        List<EnrollmentStats> stats = new ArrayList<>();

        if ("day".equals(period)) {
            startDate = endDate.minusDays(7); // 7 days of data
            while (!startDate.isAfter(endDate)) {
                LocalDateTime nextDate = startDate.plusDays(1);
                List<EnrollmentLog> enrollments = enrollmentLogRepository.findByEnrollmentDateBetween(startDate, nextDate);
                enrollments = enrollments.stream()
                        .filter(log -> locationId.equals(log.getGymLocationId()))
                        .collect(Collectors.toList());
                if (classId!=null) {
                    enrollments = enrollments.stream()
                            .filter(log -> classId.equals(log.getClassId()))
                            .collect(Collectors.toList());
                }
                long count = enrollments.size();
                stats.add(new EnrollmentStats(startDate.toLocalDate().toString(), count));
                startDate = nextDate;
            }
        } else if ("week".equals(period)) {
            startDate = endDate.minusWeeks(4); // 4 weeks of data
            while (!startDate.isAfter(endDate)) {
                LocalDateTime nextWeek = startDate.plusWeeks(1);
                List<EnrollmentLog> enrollments = enrollmentLogRepository.findByEnrollmentDateBetween(startDate, nextWeek);
                enrollments = enrollments.stream()
                        .filter(log -> locationId.equals(log.getGymLocationId()))
                        .collect(Collectors.toList());
                if (classId!=null) {
                    enrollments = enrollments.stream()
                            .filter(log -> classId.equals(log.getClassId()))
                            .collect(Collectors.toList());
                }
                long count = enrollments.size();
                stats.add(new EnrollmentStats(startDate.toLocalDate().toString(), count));
                startDate = nextWeek;
            }
        }

        return stats;
    }




}