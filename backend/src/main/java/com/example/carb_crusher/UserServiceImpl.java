    package com.example.carb_crusher;

    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;

    import java.time.Duration;
    import java.time.LocalDate;
    import java.time.LocalDateTime;
    import java.time.YearMonth;
    import java.time.temporal.ChronoUnit;
    import java.util.ArrayList;
    import java.util.Arrays;
    import java.util.List;

    @Service
    public class UserServiceImpl implements UserService {

        @Autowired
        private UserRepository userRepository;

        @Autowired
        private CheckInRepository checkInRepository;

        @Override
        public User save(User user) {
            return userRepository.save(user);
        }

        @Override
        public User findByEmail(String email) {
            return userRepository.findByEmail(email);
        }

        @Override
        public User checkInUser(String userId, LocalDateTime checkInTime) {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));

            CheckInCheckOut checkIn = new CheckInCheckOut();
            checkIn.setCheckInTime(checkInTime);

            user.getCheckInCheckOutList().add(checkIn);
            userRepository.save(user);

            // Save a new CheckIn entity to the checkIns collection
            CheckIn newCheckIn = new CheckIn();
            newCheckIn.setGymLocationId(user.getGymLocationId());
            newCheckIn.setCheckInTime(checkInTime);
            newCheckIn.setCheckInHour(checkInTime.toLocalTime().truncatedTo(ChronoUnit.HOURS));
            checkInRepository.save(newCheckIn);

            return user;
        }

        @Override
        public User checkOutUser(String userId, LocalDateTime checkOutTime) {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));

            CheckInCheckOut lastCheckInCheckOut = user.getCheckInCheckOutList().get(user.getCheckInCheckOutList().size() - 1);
            lastCheckInCheckOut.setCheckOutTime(checkOutTime);
            return userRepository.save(user);
        }


        @Override
        // UserService.java
        public List<User> findByGymLocationId(String gymLocationId) {
            return userRepository.findByGymLocationId(gymLocationId);
        }

        @Override
        public double calculateHoursSpent(String userId, LocalDateTime startTime, LocalDateTime endTime) {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));

            List<CheckInCheckOut> checkInCheckOutList = user.getCheckInCheckOutList();

            double totalHours = 0;

            for (CheckInCheckOut checkInOut : checkInCheckOutList) {
                if (checkInOut.getCheckInTime().isAfter(startTime) && checkInOut.getCheckOutTime().isBefore(endTime)) {
                    Duration duration = Duration.between(checkInOut.getCheckInTime(), checkInOut.getCheckOutTime());
                    totalHours += duration.toMinutes() / 60.0;
                }
            }

            return totalHours;
        }
        @Override
        public double calculateDailyHoursSpent(String userId, LocalDate date) {
            LocalDateTime startOfDay = date.atStartOfDay();
            LocalDateTime endOfDay = date.plusDays(1).atStartOfDay();
            return calculateHoursSpent(userId, startOfDay, endOfDay);
        }
        @Override
        public double calculateWeeklyHoursSpent(String userId, LocalDate startDateOfWeek) {
            LocalDateTime startOfWeek = startDateOfWeek.atStartOfDay();
            LocalDateTime endOfWeek = startDateOfWeek.plusWeeks(1).atStartOfDay();
            return calculateHoursSpent(userId, startOfWeek, endOfWeek);
        }

        public double calculateMonthlyHoursSpent(String userId, YearMonth month) {
            LocalDateTime startOfMonth = month.atDay(1).atStartOfDay();
            LocalDateTime endOfMonth = month.plusMonths(1).atDay(1).atStartOfDay();
            return calculateHoursSpent(userId, startOfMonth, endOfMonth);
        }

        @Override
        public List<User> findAll() {
            return userRepository.findAll();
        }

        @Override
        public List<TimeSpentStats> getTimeSpentStats(String userId, String period) {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));

            LocalDateTime now = LocalDateTime.now();
            LocalDateTime startDate;
            LocalDateTime endDate = now;

            List<TimeSpentStats> stats = new ArrayList<>();

            if ("day".equals(period)) {
                startDate = endDate.minusDays(7); // 7 days of data
            } else if ("week".equals(period)) {
                startDate = endDate.minusWeeks(4); // 4 weeks of data
            } else if ("month".equals(period)) {
                startDate = endDate.minusMonths(12); // 12 months of data
            } else {
                throw new IllegalArgumentException("Invalid period: " + period);
            }

            while (!startDate.isAfter(endDate)) {
                LocalDateTime periodEndDate;
                if ("day".equals(period)) {
                    periodEndDate = startDate.plusDays(1);
                } else if ("week".equals(period)) {
                    periodEndDate = startDate.plusWeeks(1);
                } else { // month
                    periodEndDate = startDate.plusMonths(1);
                }

                double hours = calculateHoursSpent(userId, startDate, periodEndDate);
                stats.add(new TimeSpentStats(startDate.toLocalDate().toString(), hours));

                startDate = periodEndDate;
            }

            return stats;
        }




    }

