package com.epam.webproject.model.entity;

public enum Role {
    ADMIN("ADMIN"),
    USER("USER");
    private String value;
    private Role(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}


