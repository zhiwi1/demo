package com.epam.webproject.localization;

public enum LocaleKey {
    REGISTRATION_REGISTRATION("registration.registration"),
    REGISTRATION_EMAIL("registration.email"),
    REGISTRATION_LOGIN("registration.login"),
    REGISTRATION_PASSWORD("registration.password"),
    REGISTRATION_CONFIRM("registration.confirm"),
    REGISTRATION_SIGN_UP("registration.signup");


    private String value;

    private LocaleKey(String value) {
        this.value = value;
    }


    public String getValue() {
        return value;
    }
}
