package com.optimise.common.dependency.autoconfigure.discovery;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Setter
@Getter
@ConfigurationProperties(prefix = "eureka.client")
public class EurekaClientProperties {
    private String defaultZone = "http://localhost:8761/eureka/";
    private int registryFetchIntervalSeconds = 5;

}