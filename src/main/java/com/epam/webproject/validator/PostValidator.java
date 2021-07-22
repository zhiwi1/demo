package com.epam.webproject.validator;

public class PostValidator {

    private static final String COMPLEXITY_REGEXP = "^[0-9]|(10)$";
    private static final String PASSWORD_REGEXP = "^([A-zА-яЁё0-9_!@ -]){6,}$";

    private PostValidator() {
    }

    public static boolean checkComplexity(String complexity) {
        return complexity.matches(COMPLEXITY_REGEXP);
    }


}
