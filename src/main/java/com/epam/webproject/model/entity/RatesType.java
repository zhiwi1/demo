package com.epam.webproject.model.entity;


public enum RatesType {
    NEWBIE("NEWBIE"),
    STUDENT("STUDENT"),
    HARDWORKER("HARDWORKER"),
    PROFESSIONAL("PROFESSIONAL");
    private String value;

    private RatesType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }


}
