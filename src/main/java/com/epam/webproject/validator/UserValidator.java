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
        return isMatchFounded(email, EMAIL_REGEXP);
    }

    public static boolean checkPassword(String password) {
        final String PASSWORD_REGEXP = regexpPropertyUtil.getProperty(REGEXP_PASSWORD_PROP);
        return isMatchFounded(password, PASSWORD_REGEXP);

    }

    public static boolean checkPasswordAndConfirmPassword(String password, String confirmPassword) {
        boolean result = false;
        if (password != null && confirmPassword != null) {
            result = password.equals(confirmPassword);
        }
        return result;
    }

    public static boolean checkLogin(String login) {
        final String LOGIN_REGEXP = regexpPropertyUtil.getProperty(REGEXP_LOGIN_PROP);
        return isMatchFounded(login, LOGIN_REGEXP);

    }

    private static boolean isMatchFounded(String text, String regexp) {
        return text != null && text.matches(regexp);
    }
}

