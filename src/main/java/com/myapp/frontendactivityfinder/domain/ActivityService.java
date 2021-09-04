package com.myapp.frontendactivityfinder.domain;

import com.myapp.frontendactivityfinder.client.BackendClient;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ActivityService {
    BackendClient backendClient;


        public List<Activity> getActivities() {
        return backendClient.getAllActivities();
    }

//    private Set activities;
    private static ActivityService activityService;
//
//    private ActivityService() {
//        this.activities = exampleData();
//    }
//
    public static ActivityService getInstance() {
        if (activityService == null) {
            activityService = new ActivityService();
        }
        return activityService;
    }
//
//    public Set getActivities() {
//        return new HashSet<>(activities);
//    }
//
//    public void addActivity(Activity activity) {
//        this.activities.add(activity);
//    }
//
//    private Set exampleData() {
//        Set activities = new HashSet<>();
//        activities.add(new Activity("UNO", "Gra w karty blablablablablablablablablablablablablablablablablablablablablablablabla", true));
//        activities.add(new Activity("Karty", "Gra w karty", true));
//        activities.add(new Activity("UNO", "Gra w karty blablablablablablablablablablablablablablablablablablablablablablablabla", true));
//        activities.add(new Activity("Karty", "Gra w karty", true));
//        activities.add(new Activity("UNO", "Gra w karty blablablablablablablablablablablablablablablablablablablablablablablabla", true));
//        activities.add(new Activity("Karty", "Gra w karty", true));
//        activities.add(new Activity("UNO", "Gra w karty blablablablablablablablablablablablablablablablablablablablablablablabla", true));
//        activities.add(new Activity("Karty", "Gra w karty", true));
//        activities.add(new Activity("UNO", "Gra w karty blablablablablablablablablablablablablablablablablablablablablablablabla", true));
//        activities.add(new Activity("Karty", "Gra w karty", true));activities.add(new Activity("UNO", "Gra w karty blablablablablablablablablablablablablablablablablablablablablablablabla", true));
//        activities.add(new Activity("Karty", "Gra w karty", true));activities.add(new Activity("UNO", "Gra w karty blablablablablablablablablablablablablablablablablablablablablablablabla", true));
//        activities.add(new Activity("Karty", "Gra w karty", true));
//        return activities;
//    }
}
