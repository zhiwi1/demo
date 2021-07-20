package com.epam.webproject.localization;

import java.util.ResourceBundle;

public enum Localizer {
    EN("en"),
    RU("ru");

    private final String languageCode;
    private final ResourceBundle resourceBundle;

    Localizer(String language) {
        this.languageCode = language;
        this.resourceBundle = ResourceBundle.getBundle("locale_" + language);
    }

    public ResourceBundle getResourceBundle() {
        return this.resourceBundle;
    }

    public String getLanguageCode() {
        return languageCode;
    }

   public String getText(LocaleKey key) {
        return this.resourceBundle.getString(key.getValue());
    }

    public String getText(String key) {
        return this.resourceBundle.getString(key);
    }
}
