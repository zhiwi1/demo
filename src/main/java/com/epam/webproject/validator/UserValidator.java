package com.epam.webproject.validator;

public class UserValidator extends BaseValidator {
    private static final String REGEXP_EMAIL_PROP = "regexp.email";
    private static final String REGEXP_PASSWORD_PROP = "regexp.password";
    private static final String REGEXP_LOGIN_PROP = "regexp.login";

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


}

