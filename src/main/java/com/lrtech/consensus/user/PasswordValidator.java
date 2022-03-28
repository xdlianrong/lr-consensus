package com.lrtech.consensus.user;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator {
    /**
     * Password must contain at least one digit [0-9].
     * Password must contain at least one lowercase Latin character [a-z].
     * Password must contain at least one uppercase Latin character [A-Z].
     * Password allow contain one special character like #$%&*@^_()-+=,.!?.
     * Password must contain a length of at least 8 characters and a maximum of 20 characters.
     */
    private static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,20}$";

    private static final Pattern pattern = Pattern.compile(PASSWORD_PATTERN);

    public static boolean valid(final String password) {
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}