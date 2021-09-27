package com.myapp.frontendactivityfinder.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class Bored {

    private String activity;

    @Override
    public String toString() {
        return activity + "\n";
    }
}
