package com.myapp.frontendactivityfinder.client;

import com.myapp.frontendactivityfinder.configuration.ConnectionConfig;
import com.myapp.frontendactivityfinder.domain.Activity;
import com.myapp.frontendactivityfinder.domain.Bored;
import com.myapp.frontendactivityfinder.domain.StatisticsDto;
import com.myapp.frontendactivityfinder.domain.Weather;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import java.util.*;

@Component
@RequiredArgsConstructor
public class BackendClient {
    private final RestTemplate restTemplate;
    private final ConnectionConfig connectionConfig;
    private static final Logger LOGGER = LoggerFactory.getLogger(BackendClient.class);

    @Autowired
    StatisticsDto statisticsDto;

    public Bored readBored() {
        Optional<Bored> response = Optional.ofNullable(restTemplate.postForObject(connectionConfig.getBackApiEndpoint() + "/bored/show" ,null, Bored.class));
        return response.orElse(new Bored());
    }

    public Weather readWeather(String stacja) {
            Optional<Weather> boardsResponse = Optional.ofNullable(restTemplate.getForObject(connectionConfig.getBackApiEndpoint() + "/weather/get/" + stacja, Weather.class));
            return boardsResponse.orElse(new Weather());
    }

    public Long getLikes() {
        return restTemplate.getForObject(connectionConfig.getBackApiEndpoint() + "/stat/likes", Long.class, Long.class);

    }

    public void updateStatistics(StatisticsDto statisticsDto) {
        restTemplate.put(connectionConfig.getBackApiEndpoint() + "/stat/update", statisticsDto, StatisticsDto.class);
    }

    public List<Activity> getAllActivities() {
        try {
            Optional<Activity[]> boardsResponse = Optional.ofNullable(restTemplate.getForObject(connectionConfig.getBackApiEndpoint() + "/activities", Activity[].class));
            return Arrays.asList(boardsResponse.orElse(new Activity[0]));
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            return new ArrayList<>();
        }
    }

    public List<Activity> getNewestActivities() {
        try {
            Optional<Activity[]> boardsResponse = Optional.ofNullable(restTemplate.getForObject(connectionConfig.getBackApiEndpoint() + "/newest", Activity[].class));
            return Arrays.asList(boardsResponse.orElse(new Activity[0]));
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            return new ArrayList<>();
        }
    }

    public List<Activity> getRandomActivity() {
        try {
            Optional<Activity[]> boardsResponse = Optional.ofNullable(restTemplate.getForObject(connectionConfig.getBackApiEndpoint() + "/random", Activity[].class));
            return Arrays.asList(boardsResponse.orElse(new Activity[0]));
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            return new ArrayList<>();
        }
    }

    public List<Activity> getOneActivities() {
        try {
            Optional<Activity[]> boardsResponse = Optional.ofNullable(restTemplate.getForObject(connectionConfig.getBackApiEndpoint() + "/one", Activity[].class));
            return Arrays.asList(boardsResponse.orElse(new Activity[0]));
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            return new ArrayList<>();
        }
    }

    public List<Activity> getTwoActivities() {
        try {
            Optional<Activity[]> boardsResponse = Optional.ofNullable(restTemplate.getForObject(connectionConfig.getBackApiEndpoint() + "/two", Activity[].class));
            return Arrays.asList(boardsResponse.orElse(new Activity[0]));
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            return new ArrayList<>();
        }
    }

    public List<Activity> getMoreActivities() {
        try {
            Optional<Activity[]> boardsResponse = Optional.ofNullable(restTemplate.getForObject(connectionConfig.getBackApiEndpoint() + "/more", Activity[].class));
            return Arrays.asList(boardsResponse.orElse(new Activity[0]));
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            return new ArrayList<>();
        }
    }

    public List<Activity> getOutdoorActivities() {
        try {
            Optional<Activity[]> boardsResponse = Optional.ofNullable(restTemplate.getForObject(connectionConfig.getBackApiEndpoint() + "/outdoor", Activity[].class));
            return Arrays.asList(boardsResponse.orElse(new Activity[0]));
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            return new ArrayList<>();
        }
    }

    public List<Activity> getIndoorActivities() {
        try {
            Optional<Activity[]> boardsResponse = Optional.ofNullable(restTemplate.getForObject(connectionConfig.getBackApiEndpoint() + "/indoor", Activity[].class));
            return Arrays.asList(boardsResponse.orElse(new Activity[0]));
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            return new ArrayList<>();
        }
    }

    public List<Activity> getSummerActivities() {
        try {
            Optional<Activity[]> boardsResponse = Optional.ofNullable(restTemplate.getForObject(connectionConfig.getBackApiEndpoint() + "/summer", Activity[].class));
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

    public List<Activity> getEducationalActivities() {
        try {
            Optional<Activity[]> boardsResponse = Optional.ofNullable(restTemplate.getForObject(connectionConfig.getBackApiEndpoint() + "/educational", Activity[].class));
            return Arrays.asList(boardsResponse.orElse(new Activity[0]));
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            return new ArrayList<>();
        }
    }

    public List<Activity> getArtActivities() {
        try {
            Optional<Activity[]> boardsResponse = Optional.ofNullable(restTemplate.getForObject(connectionConfig.getBackApiEndpoint() + "/art", Activity[].class));
            return Arrays.asList(boardsResponse.orElse(new Activity[0]));
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            return new ArrayList<>();
        }
    }

    public List<Activity> getMotionActivities() {
        try {
            Optional<Activity[]> boardsResponse = Optional.ofNullable(restTemplate.getForObject(connectionConfig.getBackApiEndpoint() + "/motion", Activity[].class));
            return Arrays.asList(boardsResponse.orElse(new Activity[0]));
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            return new ArrayList<>();
        }
    }
}