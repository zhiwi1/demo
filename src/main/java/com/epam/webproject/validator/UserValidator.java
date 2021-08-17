package com.epam.webproject.validator;


import com.epam.webproject.util.RegexpPropertyUtil;

public class UserValidator {
    private static final String REGEXP_EMAIL_PROP = "regexp.email";
    private static final String REGEXP_PASSWORD_PROP = "regexp.password";
    private static final String REGEXP_LOGIN_PROP = "regexp.login";
    private static final RegexpPropertyUtil regexpPropertyUtil = RegexpPropertyUtil.getInstance();

    private UserValidator() {
    }

    public static boolean checkEmail(String email) {
        final String EMAIL_REGEXP = regexpPropertyUtil.getProperty(REGEXP_EMAIL_PROP);
        return email.matches(EMAIL_REGEXP);
    }

    public static boolean checkPassword(String password) {
        final String PASSWORD_REGEXP = regexpPropertyUtil.getProperty(REGEXP_PASSWORD_PROP);
        return password.matches(PASSWORD_REGEXP);
    }

    public static boolean checkPasswordAndConfirmPassword(String password, String confirmPassword) {
        return password.equals(confirmPassword);
    }

    public static boolean checkLogin(String text) {
        final String LOGIN_REGEXP = regexpPropertyUtil.getProperty(REGEXP_LOGIN_PROP);
        return text.matches(LOGIN_REGEXP);
    }
}

