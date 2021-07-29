package com.epam.webproject.localization;

import java.util.ResourceBundle;

public enum Localization {
    EN("en"),
    RU("ru");

    private final String language;
    private final ResourceBundle resourceBundle;
   Localization(String language){
        this.language=language;
        this.resourceBundle = ResourceBundle.getBundle("locale_" + language);
    }


    public ResourceBundle getResourceBundle() {
        return this.resourceBundle;
    }


    public String getText(LocalizationKey key) {
        return this.resourceBundle.getString(key.getValue());
    }

    public String getText(String key) {
        return this.resourceBundle.getString(key);
    }
}