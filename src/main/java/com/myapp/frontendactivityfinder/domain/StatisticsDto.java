package com.myapp.frontendactivityfinder.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
public class StatisticsDto {

    private Long id;

    private Long likeStat;

    private Long artStat;

    private Long eduStat;

    private Long motionStat;

    private Long carStat;

    private Long oneStat;

    private Long twoStat;

    private Long moreStat;

    private Long outdoorStat;

    private Long indoorStat;

    private Long summerStat;

    private Long winterStat;
}
