package com.epam.webproject.validator;

public class AnswerValidator {
    private static final String ANSWER_REGEXP = "^.{10,}+$";


    private AnswerValidator() {}

    public static boolean checkAnswer(String comment) {
        return comment.matches(ANSWER_REGEXP);
    }

}
