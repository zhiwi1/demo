package com.epam.webproject.model.entity;

public enum CorrectnessOfAnswer {
    CORRECT("CORRECT"),
    INCORRECT("INCORRECT");

    private String value;

    private CorrectnessOfAnswer(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
