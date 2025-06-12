package com.optimise.common.dependency.properties;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import org.springframework.validation.annotation.Validated;

@Validated
@ConfigurationProperties(prefix = "common")
@Getter
@Setter
public class CommonProperties {
    private Eureka eureka = new Eureka();
    private Security security = new Security();
    //private Resilience resilience = new Resilience();

    // Getters, Setters et classes internes...

    // Getters et Setters

    @Data
    public static class Eureka {
        private boolean enabled = true;
        private String defaultZone = "http://localhost:8761/eureka/";
        private int registryFetchIntervalSeconds = 5;

        // Getters et Setters
    }

    @Data
    public static class Security {
        private OAuth2 oauth2 = new OAuth2();

        @Data
        public static class OAuth2 {
            private String issuerUri = "http://localhost:8080/auth/realms/master";
            private String jwkSetUri;

            // Getters et Setters
        }

        // Getters et Setters
    }
}