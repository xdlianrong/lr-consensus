package com.lrtech.consensus.config;

import com.lrtech.consensus.config.producer.ProducerProperties;
import com.lrtech.consensus.security.auth.WebSecurityAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppConfiguration {

    // Keep Spring Security updated about session lifecycle events
    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }

    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer(ProducerProperties producerProperties) {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                String allowedOrigins = producerProperties.getCors().getAllowedOrigins();
                if (StringUtils.hasLength(allowedOrigins)) {
                    registry.addMapping("/**").allowedOrigins(allowedOrigins);
                }
            }

            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("index");

                registry.addViewController(WebSecurityAdapter.PATTERN_EXC_SESSION_EXPIRED)
                        .setViewName(WebSecurityAdapter.PATTERN_EXC_SESSION_EXPIRED.substring(1));
                registry.addRedirectViewController(WebSecurityAdapter.PATTERN_WELL_KNOWN_CHANGE_PASSWORD_PATTERN,
                        "/");
            }
        };
    }
}