package com.lrtech.consensus.config;

import com.lrtech.consensus.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InitConfiguration {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${lrtech.default-admin-username}")
    private String username;

    @Value("${lrtech.default-admin-name}")
    private String name;

    @Bean
    public ApplicationRunner applicationRunner(UserService userService) {
        return new ApplicationRunner() {
            @Override
            public void run(ApplicationArguments args) throws Exception {
            }
        };
    }
}