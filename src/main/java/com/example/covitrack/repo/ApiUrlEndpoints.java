package com.example.covitrack.repo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "api-url")
public class ApiUrlEndpoints {
    
    private String GLOBAL_DATA_URL;

    public ApiUrlEndpoints() {
    }

    public String getGLOBAL_DATA_URL() {
        return GLOBAL_DATA_URL;
    }

    public void setGLOBAL_DATA_URL(String gLOBAL_DATA_URL) {
        GLOBAL_DATA_URL = gLOBAL_DATA_URL;
    }

}
