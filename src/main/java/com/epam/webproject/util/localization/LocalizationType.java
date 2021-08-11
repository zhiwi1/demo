package com.epam.webproject.util.localization;

import java.util.ResourceBundle;

public enum LocalizationType {
    EN("en"),
    RU("ru");

    private final String language;
    private final ResourceBundle resourceBundle;

    LocalizationType(String language) {
        this.language = language;
        this.resourceBundle = ResourceBundle.getBundle("locale_" + language);
    }

    public String getText(String key) {
        return this.resourceBundle.getString(key);
    }
}