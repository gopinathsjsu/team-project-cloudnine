package com.example.carb_crusher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController


@RequestMapping("/api/activities")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @PostMapping
    public Activity save(@RequestBody Activity activity) {
        return activityService.save(activity);
    }

    @GetMapping("/{id}")
    public Activity findById(@PathVariable String id) {
        return activityService.findById(id);
    }

    @GetMapping
    public List<Activity> findAll() {
        return activityService.findAll();
    }

    @GetMapping("/user/{userId}")
    public List<Activity> findByUserId(@PathVariable String userId) {
        return activityService.findByUserId(userId);
    }

    @PostMapping("/addActivityToUser")
    public Activity addActivityToUser(@RequestParam String userId, @RequestBody Activity activity) {
        return activityService.addActivityToUser(userId, activity);
    }

    @GetMapping("/user/{userId}/activities")
    public List<Activity> getActivitiesForUser(@PathVariable String userId) {
        return activityService.getActivitiesForUser(userId);
    }

    @GetMapping("/user/{userId}/activities/recent")
    public List<Activity> getRecentActivitiesForUser(@PathVariable String userId, @RequestParam String period) {
        return activityService.getRecentActivitiesForUser(userId, period);
    }


    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable String id) {
        activityService.deleteById(id);
    }
}
