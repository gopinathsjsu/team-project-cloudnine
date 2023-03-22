package com.example.carb_crusher;

import java.util.List;

public interface ActivityService {
    Activity save(Activity activity);
    Activity findById(String id);
    List<Activity> findByUserId(String userId);
    List<Activity> findAll();
    Activity addActivityToUser(String userId, Activity activity);

    List<Activity> getActivitiesForUser(String userId);

    List<Activity> getRecentActivitiesForUser(String userId, String period);


    void deleteById(String id);
}
