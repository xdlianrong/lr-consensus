package com.lrtech.consensus.security.auth.lianrong;

import com.lrtech.consensus.security.auth.Role;
import com.lrtech.consensus.security.auth.WebSecurityAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@EnableWebSecurity
@ConditionalOnProperty(prefix = "lrtech", value = "operator", havingValue = "lianrong")
public class LianrongWebSecurityAdapter extends WebSecurityAdapter {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    String[] PATTERNS_PERMITTED = {PATTERN_EXC_SESSION_EXPIRED};

    private final AuthenticationSuccessHandler authenticationSuccessHandler
            = new SavedRequestAwareAuthenticationSuccessHandler() {
        @Override
        public void onAuthenticationSuccess(
                HttpServletRequest request, HttpServletResponse response, Authentication authentication)
                throws IOException, ServletException {
//            request.getSession().removeAttribute(AbstractCasFilter.CONST_CAS_ASSERTION);
            super.onAuthenticationSuccess(request, response, authentication);
        }
    };

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        http.authorizeRequests()
                .antMatchers(PATTERNS_PERMITTED).permitAll()
                .antMatchers("/register/**").hasRole(Role.ROLE_ADMIN)
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .successHandler(authenticationSuccessHandler)
                .permitAll()
                .and()
                .rememberMe()
                .and()
                .sessionManagement()
                .invalidSessionUrl("/login")
                .maximumSessions(2).expiredUrl(PATTERN_EXC_SESSION_EXPIRED);
    }

    @Override
    protected String logoutSuccessUrl(Authentication authentication) {
        return "/login";
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(new UserDetailsService() {

            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return null;
            }
        });
    }
}