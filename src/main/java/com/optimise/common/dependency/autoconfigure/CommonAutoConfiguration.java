package com.optimise.common.dependency.autoconfigure;

import com.optimise.common.dependency.autoconfigure.discovery.EurekaAutoConfiguration;
import com.optimise.common.dependency.autoconfigure.discovery.EurekaClientProperties;
import com.optimise.common.dependency.config.GlobalExceptionHandler;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import com.optimise.common.dependency.properties.CommonProperties;
import org.springframework.core.env.Environment;

@AutoConfiguration
@EnableConfigurationProperties({CommonProperties.class, EurekaClientProperties.class})
@Import({
        EurekaAutoConfiguration.class,
        SecurityAutoConfiguration.class,
        Resilience4JAutoConfiguration.class
})
@ConditionalOnClass(name = "org.springframework.boot.SpringApplication")
public class CommonAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean
    public CommonLibraryInitializer commonLibraryInitializer(Environment env,
                                                             CommonProperties properties) {
        return new CommonLibraryInitializer(env, properties);
    }

    // Configuration commune supplémentaire
    @Bean
    @ConditionalOnMissingBean
    public GlobalExceptionHandler globalExceptionHandler() {
        return new GlobalExceptionHandler();
    }
}
