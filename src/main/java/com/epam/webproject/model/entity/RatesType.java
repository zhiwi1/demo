package com.epam.webproject.model.entity;


public enum RatesType {
    NEWBIE("newbie"),
    STUDENT("student"),
    HARD_WORKER("hard-worker"),
    PROFESSIONAL("professional");

    private final String value;

    private RatesType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("RatesType{");
        sb.append("value='").append(value).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
