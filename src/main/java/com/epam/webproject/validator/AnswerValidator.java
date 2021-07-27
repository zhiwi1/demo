package com.epam.webproject.validator;

public class AnswerValidator {
    private static final String ANSWER_REGEXP = "^.+$";


    private AnswerValidator() {}

    public static boolean checkAnswer(String answer) {
        return answer.matches(ANSWER_REGEXP);
    }

}
