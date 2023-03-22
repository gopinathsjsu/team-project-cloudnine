package com.example.carb_crusher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Activity save(Activity activity) {
        return activityRepository.save(activity);
    }

    @Override
    public Activity findById(String id) {
        return activityRepository.findById(id).orElse(null);
    }

    @Override
    public List<Activity> findAll() {
        return activityRepository.findAll();
    }

    @Override
    public List<Activity> findByUserId(String userId) {
        return activityRepository.findByUserId(userId);
    }
    @Override
    public Activity addActivityToUser(String userId, Activity activity) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));
        activity.setUserId(userId);
        activityRepository.save(activity);

        // Add the activity to the user's activities list and save the user
        if (user.getActivities() == null) {
            user.setActivities(new ArrayList<>());
        }
        user.getActivities().add(activity);
        userRepository.save(user);

        return activity;
    }
    @Override
    public List<Activity> getActivitiesForUser(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));
        return user.getActivities();
    }

    @Override
    public List<Activity> getRecentActivitiesForUser(String userId, String period) {
        List<Activity> activities = this.getActivitiesForUser(userId);
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime startDate;

        switch(period) {
            case "week":
                startDate = now.minusWeeks(1);
                break;
            case "month":
                startDate = now.minusMonths(1);
                break;
            case "90days":
                startDate = now.minusDays(90);
                break;
            default:
                throw new IllegalArgumentException("Invalid period: " + period);
        }

        List<Activity> recentActivities = activities.stream()
                .filter(activity -> activity.getStartTime() != null && !activity.getStartTime().isBefore(startDate))
                .collect(Collectors.toList());

        return recentActivities;
    }



    @Override
    public void deleteById(String id) {
        activityRepository.deleteById(id);
    }
}
