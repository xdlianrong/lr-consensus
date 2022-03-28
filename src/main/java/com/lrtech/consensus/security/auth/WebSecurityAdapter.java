package com.lrtech.consensus.security.auth;

import com.lrtech.consensus.user.UserService;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import java.util.Collection;

import static org.springframework.security.config.Customizer.withDefaults;

public abstract class WebSecurityAdapter extends WebSecurityConfigurerAdapter {
    Logger logger = LoggerFactory.getLogger(getClass());
    public static final String PATTERN_WELL_KNOWN_CHANGE_PASSWORD_PATTERN = "/.well-known/change-password";
    public static final String PATTERN_EXC_SESSION_EXPIRED = "/exc-session-expired";

    @Value("${spring.profiles.active}")
    private String activeProfile;

    String[] PATTERNS_PERMITTED = {
            "/login/**",
            "/user/register/one",
            "/register/**"
    };

    String[] PATTERNS_DOCS = {
            "/actuator/**",
            "/v3/api-docs",
            "/swagger-ui.html"
    };

    @Autowired
    protected UserService userService;

    @Getter
    public static final class UserLocal extends org.springframework.security.core.userdetails.User {
        private final Long userId;
        private final Long departmentId;

        public UserLocal(String username, String password, Collection<? extends GrantedAuthority> authorities,
                         Long userId, Long departmentId) {
            super(username, password, authorities);
            this.userId = userId;
            this.departmentId = departmentId;
        }

        public UserLocal(String username, String password, boolean enabled, boolean accountNonExpired,
                         boolean credentialsNonExpired, boolean accountNonLocked,
                         Collection<? extends GrantedAuthority> authorities, Long userId, Long departmentId) {
            super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
            this.userId = userId;
            this.departmentId = departmentId;
        }
    }

    private final LogoutSuccessHandler logoutSuccessHandler =
            (request, response, authentication) -> response.sendRedirect(logoutSuccessUrl(authentication));

    protected abstract String logoutSuccessUrl(Authentication authentication);

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors(withDefaults())
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(PATTERNS_PERMITTED).permitAll()
                .and()
                .logout()
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .logoutSuccessHandler(logoutSuccessHandler)
                .permitAll();

        resourceOnProduction(http);
    }

    private void resourceOnProduction(HttpSecurity http) throws Exception {
        if ("dev".equals(activeProfile)) {
            http.authorizeRequests()
                    .antMatchers(PATTERNS_DOCS).permitAll();
        } else {
            http.authorizeRequests()
                    .antMatchers(PATTERNS_DOCS).hasRole(Role.ROLE_ADMIN);
        }
    }
}