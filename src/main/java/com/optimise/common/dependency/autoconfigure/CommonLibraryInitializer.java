package com.optimise.common.dependency.autoconfigure;

import com.optimise.common.dependency.properties.CommonProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CommonLibraryInitializer {

    private final Environment env;
    private final CommonProperties properties;

    public CommonLibraryInitializer(Environment env,
                                    CommonProperties properties) {
        this.env = env;
        this.properties = properties;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void initialize() {
        logCommonConfig();
    }

    private void logCommonConfig() {
        if (log.isInfoEnabled()) {
            log.info("Common Library Configuration:");
            log.info("-> Eureka Enabled: {}", properties.getEureka().isEnabled());
            log.info("-> Security OAuth2 Issuer: {}",
                    properties.getSecurity().getOauth2().getIssuerUri());
        }
    }
}
