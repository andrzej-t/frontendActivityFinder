package com.myapp.frontendactivityfinder.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Activity {
    private String nazwa;
    private String opis;
    private Boolean ulubione;
}
