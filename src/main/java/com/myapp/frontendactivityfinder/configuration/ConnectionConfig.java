package com.myapp.frontendactivityfinder.configuration;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class ConnectionConfig {
//    @Value("${http://localhost:8080/}")
    private String backApiEndpoint="http://localhost:8080/";
}
