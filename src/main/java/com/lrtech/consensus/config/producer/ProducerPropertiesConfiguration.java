package com.lrtech.consensus.config.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProducerPropertiesConfiguration {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Bean
    @ConfigurationProperties(prefix = "lrtech")
    public ProducerProperties getProducerProperties() {
        return new ProducerProperties();
    }
}