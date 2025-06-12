package com.optimise.common.dependency.autoconfigure.discovery;

import com.netflix.discovery.EurekaClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.eureka.EurekaClientAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EurekaClientConfigBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.HashMap;
import java.util.Map;

@Configuration
@ConditionalOnClass(EurekaClient.class)
@EnableConfigurationProperties(EurekaClientProperties.class)
@ConditionalOnProperty(
        value = {"spring.cloud.discovery.enabled", "eureka.client.enabled"},
        havingValue = "true",
        matchIfMissing = true
)
public class EurekaAutoConfiguration extends EurekaClientAutoConfiguration {

    private final EurekaClientProperties properties;
    private final ConfigurableEnvironment environment;

    public EurekaAutoConfiguration(EurekaClientProperties properties, ConfigurableEnvironment environment) {
        super(environment);
        this.properties = properties;
        this.environment = environment;
    }

    @Bean
    @Primary
    @Override
    public EurekaClientConfigBean eurekaClientConfigBean(ConfigurableEnvironment environment) {
        EurekaClientConfigBean config = super.eurekaClientConfigBean(environment);

        if (config.getServiceUrl().isEmpty()) {
            Map<String, String> serviceUrl = new HashMap<>();
            serviceUrl.put("defaultZone", properties.getDefaultZone());
            config.setServiceUrl(serviceUrl);
        }

        config.setRegistryFetchIntervalSeconds(properties.getRegistryFetchIntervalSeconds());
        config.setInstanceInfoReplicationIntervalSeconds(30);
        config.setEurekaServerReadTimeoutSeconds(8);
        config.setEurekaServerConnectTimeoutSeconds(5);
        config.setEurekaServerTotalConnections(20);
        config.setEurekaServerTotalConnectionsPerHost(10);

        return config;
    }
}