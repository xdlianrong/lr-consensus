package com.lrtech.consensus.config.operators;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OperatorPropertiesConfiguration {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Bean
    @ConfigurationProperties(prefix = "lianrong")
    public OperatorProperties getLianrongProperties() {
        return new OperatorProperties();
    }

    @Bean
    @ConfigurationProperties(prefix = "shunfeng")
    public OperatorProperties getShunfengProperties() {
        return new OperatorProperties();
    }
}