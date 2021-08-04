package com.epam.webproject.validator;

public class TaskValidator {

    private static final String COMPLEXITY_REGEXP = "^[0-9]|(10)$";
    private static final String TEXT_REGEXP = ".+";

    private TaskValidator() {
    }

    public static boolean checkComplexity(String complexity) {
        return complexity.matches(COMPLEXITY_REGEXP);
    }

    public static boolean checkLength(String text) {
        return text.matches(TEXT_REGEXP);
    }

}
