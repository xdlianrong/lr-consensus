package com.lrtech.consensus;

import com.lrtech.consensus.security.auth.Role;
import com.lrtech.consensus.security.auth.WebSecurityAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.Collection;

public class BasicController {

    protected Long userId() {
        return userLocal().getUserId();
    }

    protected Long departmentId() {
        return userLocal().getDepartmentId();
    }

    protected boolean hasRole(String roleName) {
        String concat = Role.concat(roleName);
        for (GrantedAuthority a : userLocal().getAuthorities()) {
            return a.getAuthority().equals(concat);
        }
        return false;
    }

    protected WebSecurityAdapter.UserLocal userLocal() {
        return (WebSecurityAdapter.UserLocal) authentication().getPrincipal();
    }

    protected String encodedUsername() {
        return authentication().getName();
    }

    protected Authentication authentication() {
        Authentication a = SecurityContextHolder.getContext().getAuthentication();
        if (a == null) {
            return EMPTY_AUTHENTICATION;
        }
        return a;
    }

    protected static final Authentication EMPTY_AUTHENTICATION = new EmptyAuthentication();

    public static class EmptyAuthentication implements Authentication {

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return new ArrayList<>();
        }

        @Override
        public Object getCredentials() {
            return null;
        }

        @Override
        public Object getDetails() {
            return null;
        }

        @Override
        public Object getPrincipal() {
            return null;
        }

        @Override
        public boolean isAuthenticated() {
            return false;
        }

        @Override
        public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        }

        @Override
        public String getName() {
            return "";
        }
    }
}