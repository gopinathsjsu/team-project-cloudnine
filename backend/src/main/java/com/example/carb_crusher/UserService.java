package com.example.carb_crusher;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.List;

public interface UserService {
    User save(User user);
    User findByEmail(String email);

    User checkInUser(String userId, LocalDateTime checkInTime);
    User checkOutUser(String userId, LocalDateTime checkInTime);
    double calculateHoursSpent(String userId, LocalDateTime startTime, LocalDateTime endTime);
    double calculateDailyHoursSpent(String userId, LocalDate date);
    double calculateWeeklyHoursSpent(String userId, LocalDate startDateOfWeek);
    double calculateMonthlyHoursSpent(String userId, YearMonth month);
    List<User> findByGymLocationId(String gymLocationId);
    List<User> findAll();

    List<TimeSpentStats> getTimeSpentStats(String userId, String period);


}

