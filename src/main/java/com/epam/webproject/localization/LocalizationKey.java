package com.epam.webproject.localization;

public enum LocalizationKey {
    LOGIN_ERROR_MESSAGE("login.error"),
    REGISTRATION_CHECK_DATA_MESSAGE("registration.check"),
    REGISTRATION_LOGIN_OR_EMAIL_EXISTS("registration.le_exists");
    private final String key;

    LocalizationKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return this.key;
    }
}

