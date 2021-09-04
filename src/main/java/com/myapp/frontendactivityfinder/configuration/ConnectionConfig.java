package com.myapp.frontendactivityfinder.configuration;

import lombok.Getter;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class ConnectionConfig {
    private String backApiEndpoint="http://localhost:8080/";
}
