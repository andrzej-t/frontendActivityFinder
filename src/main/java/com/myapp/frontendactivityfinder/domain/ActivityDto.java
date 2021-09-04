package com.myapp.frontendactivityfinder.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ActivityDto {

    private Long id;
    private String name;
    private String description;
    private Integer minTime;
    private Integer maxTime;
    private Boolean onePerson;
    private Boolean twoPeople;
    private Boolean morePeople;
    private Boolean outdoor;
    private Boolean indoor;
    private Boolean summer;
    private Boolean winter;
    private Boolean inCar;
    private Boolean educational;
    private Boolean art;
    private Boolean motion;
    private Boolean favourite;
}
