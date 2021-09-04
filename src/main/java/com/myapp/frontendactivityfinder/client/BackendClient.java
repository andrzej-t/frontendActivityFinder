package com.myapp.frontendactivityfinder.client;

import com.myapp.frontendactivityfinder.configuration.ConnectionConfig;
import com.myapp.frontendactivityfinder.domain.Activity;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import java.util.*;

@Service
@RequiredArgsConstructor
public class BackendClient {
    private final RestTemplate restTemplate;
    private final ConnectionConfig connectionConfig;
    private static final Logger LOGGER = LoggerFactory.getLogger(BackendClient.class);

    public List<Activity> getAllActivities() {
        try {
            Optional<Activity[]> boardsResponse = Optional.ofNullable(restTemplate.getForObject(connectionConfig.getBackApiEndpoint() + "/activities", Activity[].class));
            return Arrays.asList(boardsResponse.orElse(new Activity[0]));
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            return new ArrayList<>();
        }
    }

    public List<Activity> getWinterActivities() {
        try {
            Optional<Activity[]> boardsResponse = Optional.ofNullable(restTemplate.getForObject(connectionConfig.getBackApiEndpoint() + "/winter", Activity[].class));
            return Arrays.asList(boardsResponse.orElse(new Activity[0]));
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            return new ArrayList<>();
        }
    }

    public List<Activity> getInCarActivities() {
        try {
            Optional<Activity[]> boardsResponse = Optional.ofNullable(restTemplate.getForObject(connectionConfig.getBackApiEndpoint() + "/car", Activity[].class));
            return Arrays.asList(boardsResponse.orElse(new Activity[0]));
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            return new ArrayList<>();
        }
    }

}
