package com.myapp.frontendactivityfinder.service;

import com.myapp.frontendactivityfinder.client.BackendClient;
import com.myapp.frontendactivityfinder.domain.Activity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
public class ActivityService {

    @Autowired
    BackendClient backendClient;

    public List<Activity> findByTitle(String text) {
        return backendClient.getNameActivities().stream().filter(activity -> activity.getName().contains(text)).collect(Collectors.toList());
    }

}
