package com.lrtech.consensus.security.auth;

public class Role {
    public static final String PREFIX = "ROLE_";
    public static final String ROLE_ADMIN = "sys_admin";

    public static String concat(String roleName) {
        return Role.PREFIX + roleName;
    }
}