package com.myapp.frontendactivityfinder.domain;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Data
public class Weather {

    private String stacja;

    private String temperatura;

    private String predkosc_wiatru;

    private String suma_opadu;

    private String cisnienie;

    @Override
    public String toString() {
        return "POGODA: " + stacja + ";"  + "\n" +
                "temperatura: " + temperatura + "\u00B0C;" + "\n" +
                "predkość wiatru: " + predkosc_wiatru + "km/h;" + "\n" +
                "suma opadu: " + suma_opadu + "mm;" + "\n" +
                "ciśnienie: " + cisnienie + "hpa;" + "\n";
    }
}
