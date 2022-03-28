package com.lrtech.consensus.user;

import org.springframework.util.StringUtils;

public abstract class UsernameEncoder {

    public String encode(String from, String username) {
        if (!StringUtils.hasLength(from)) {
            return username;
        }
        return "{" + from + "}" + username;
    }

    public String decode(String username) {
        return username.substring(username.indexOf("}") + 1);
    }

    public boolean usernameFromLocal(String encodedUsername) {
        return StringUtils.hasLength(encodedUsername) && encodedUsername.contains(fromLocal());
    }

    public abstract String fromLocal();

    public abstract String fromCas();
}