package com.epam.webproject.model.entity.type;

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


