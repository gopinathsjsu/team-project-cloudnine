package com.example.carb_crusher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CheckInServiceImpl implements CheckInService{
    @Autowired
    private CheckInRepository checkInRepository;

    @Override
    public List<CheckInStats> getCheckInStats(String period, String locationId) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime startDate;
        LocalDateTime endDate = now;

        List<CheckInStats> stats = new ArrayList<>();

        if ("hour".equals(period)) {
            startDate = endDate.minusDays(1); // 24 hours of data
            for (LocalDateTime date = startDate; date.isBefore(endDate); date = date.plusHours(1)) {
                long count = checkInRepository.countByCheckInTimeBetweenAndGymLocationId(date, date.plusHours(1), locationId);
                stats.add(new CheckInStats(date.toString(), count));
            }
        } else if ("day".equals(period)) {
            startDate = endDate.minusDays(7); // 7 days of data
            for (LocalDateTime date = startDate; date.isBefore(endDate); date = date.plusDays(1)) {
                long count = checkInRepository.countByCheckInTimeBetweenAndGymLocationId(date, date.plusDays(1), locationId);
                stats.add(new CheckInStats(date.toLocalDate().toString(), count));
            }
        } else if ("week".equals(period)) {
            startDate = endDate.minusWeeks(4); // 4 weeks of data
            for (LocalDateTime date = startDate; date.isBefore(endDate); date = date.plusWeeks(1)) {
                long count = checkInRepository.countByCheckInTimeBetweenAndGymLocationId(date, date.plusWeeks(1), locationId);
                stats.add(new CheckInStats(date.toLocalDate().toString(), count));
            }
        } else if ("weekday".equals(period)) {
            startDate = endDate.minusWeeks(4); // 4 weeks of data
            for (LocalDateTime date = startDate; date.isBefore(endDate); date = date.plusDays(1)) {
                // only count check-ins on Monday through Friday
                if (date.getDayOfWeek() != DayOfWeek.SATURDAY && date.getDayOfWeek() != DayOfWeek.SUNDAY) {
                    long count = checkInRepository.countByCheckInTimeBetweenAndGymLocationId(date, date.plusDays(1), locationId);
                    stats.add(new CheckInStats(date.toLocalDate().toString(), count));
                }
            }
        } else if ("weekend".equals(period)) {
            startDate = endDate.minusWeeks(4); // 4 weeks of data
            for (LocalDateTime date = startDate; date.isBefore(endDate); date = date.plusDays(1)) {
                // only count check-ins on Saturday and Sunday
                if (date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY) {
                    long count = checkInRepository.countByCheckInTimeBetweenAndGymLocationId(date, date.plusDays(1), locationId);
                    stats.add(new CheckInStats(date.toLocalDate().toString(), count));
                }
            }
        }
        return stats;
    }
}




