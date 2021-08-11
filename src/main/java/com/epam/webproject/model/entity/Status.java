package com.epam.webproject.model.entity;

public enum Status {
    NORMAL("NORMAL"),
    BLOCKED("BLOCKED");
    private String value;

    private Status(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}